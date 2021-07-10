package com.excmul.domain.category;

import com.excmul.domain.category.service.CategoryService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryInitializer {
    @Test
    @Transactional
    @Rollback(false)
    public void insertCategoryTest(@Autowired CategoryService categoryService) {
        List<CategoryVO> data = loadBunjangCategoryData();
        for (CategoryVO iCategory : data) {
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

    private List<CategoryVO> loadBunjangCategoryData() {
        List<CategoryVO> data = new ArrayList<>();
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

    private void parseBunjangCategoryJson(JsonNode jsonNode, List<CategoryVO> data, CategoryVO parentCategory) {
        String name = jsonNode.get("title").asText();
        if (parentCategory == null && isExcludeCategory(name))
            return;
        CategoryVO iCategoryVO = CategoryVO.builder()
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
