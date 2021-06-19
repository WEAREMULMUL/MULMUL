package com.excmul.domain.category;

import com.excmul.domain.category.dto.CategoryNode;
import com.excmul.domain.category.service.CategoryService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
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

import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
public class CategoryTest {
    private static CategoryNode categoryNode;

    @BeforeAll
    public static void findCategoryNodes(@Autowired CategoryService categoryService) {
        categoryNode = categoryService.findCategoryNode(3);
    }

    //@Test
    @DisplayName("Load Category Test")
    @Transactional(readOnly = true)
    public void loadCategoryCountTest() {
        assertTrue("루트 카테고리의 갯수가 일치하지 않습니다.", categoryNode.getChildrenCategory().size() == 11);
    }

    //@Test
    @DisplayName("Category View")
    @Transactional(readOnly = true)
    public void categoryViewTest() {
        for (CategoryNode iCategoryNode : categoryNode.getChildrenCategory())
            viewCategoryNodes(iCategoryNode);
    }

    private void viewCategoryNodes(CategoryNode categoryNode) {
        System.out.println(categoryNode.getCode() + " = " + categoryNode.getName());
        for (CategoryNode iCategoryNode : categoryNode.getChildrenCategory())
            viewCategoryNodes(iCategoryNode);
    }

    // 기본 카테고리 데이터를 삽입하기 위한 메소드 입니다!!!
    // (번개 장터에서 가져옴)
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
