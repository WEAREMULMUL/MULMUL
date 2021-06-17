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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
public class CategoryTest {
    private static List<CategoryNode> categoryNodes;

    @BeforeAll
    public static void findCategoryNodes(@Autowired CategoryService categoryService) {
        categoryNodes = categoryService.findRootCategoryNode();
    }

    @Test
    @DisplayName("Load Category Test")
    public void loadCategoryCountTest() {
        assertTrue("루트 카테고리의 갯수가 일치하지 않습니다.", categoryNodes.size() == 18);
    }

    @Test
    @DisplayName("Category View")
    public void categoryViewTest() {
        for (CategoryNode iCategoryNode : categoryNodes)
            viewCategoryNodes(iCategoryNode);
    }

    private void viewCategoryNodes(CategoryNode categoryNode) {
        System.out.println(categoryNode.getCode() + " = " + categoryNode.getName());
        for (CategoryNode iCategoryNode : categoryNode.getChildCategory())
            viewCategoryNodes(iCategoryNode);
    }

    // 기본 카테고리 데이터를 삽입하기 위한 메소드 입니다!!!
    // (번개 장터에서 가져옴)
    public void insertCategoryTest(@Autowired CategoryService categoryService) {
        List<CategoryVO> data = loadBunjangCategoryData();
        for (CategoryVO iCategory : data)
            categoryService.insertCategory(iCategory);
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
