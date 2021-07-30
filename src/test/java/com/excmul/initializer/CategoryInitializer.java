package com.excmul.initializer;

import com.excmul.category.application.CategoryService;
import com.excmul.category.domain.CategoryEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SpringBootTest
public class CategoryInitializer {
    @Autowired
    private CategoryService categoryService;

    @Test
    @Transactional
    @Rollback(false)
    public void insertCategory() {
        List<CategoryEntity> data = loadBunjangCategoryData();
        for (CategoryEntity iCategory : data) {
            categoryService.insertCategory(iCategory);
        }
    }

    private static final Set<String> excludeCategoryNames = Set.of("구인구직", "커뮤니티", "기타", "원룸", "번개", "지역", "재능");
    private boolean isExcludeCategory(String categoryName) {
        for (String iExcludeCategoryName : excludeCategoryNames) {
            if (categoryName.contains(iExcludeCategoryName))
                return true;
        }
        return false;
    }

    private List<CategoryEntity> loadBunjangCategoryData() {
        List<CategoryEntity> data = new ArrayList<>();
        ClassPathResource classPathResource = new ClassPathResource("data/번개장터 카테고리.json");
        JsonNode jsonNode;
        try {
            jsonNode = new ObjectMapper().readTree(classPathResource.getURL());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (JsonNode iCategoryNode : jsonNode.get("categories"))
            parseBunjangCategoryJson(iCategoryNode, data, null);
        return data;
    }

    private void parseBunjangCategoryJson(JsonNode jsonNode, List<CategoryEntity> data, CategoryEntity parentCategory) {
        String name = jsonNode.get("title").asText();
        if (parentCategory == null && isExcludeCategory(name))
            return;
        CategoryEntity iCategoryVO = CategoryEntity.builder()
                .parent(parentCategory)
                .name(name)
                .build();
        data.add(iCategoryVO);

        if (jsonNode.has("categories")) {
            for (JsonNode iCategoryNode : jsonNode.get("categories")) {
                parseBunjangCategoryJson(iCategoryNode, data, iCategoryVO);
            }
        }
    }
}
