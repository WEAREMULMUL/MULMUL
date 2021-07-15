package com.excmul.domain.category.dto;

import com.excmul.domain.category.vo.CategoryCode;
import com.excmul.domain.category.entity.CategoryEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.CollectionUtils;

import java.util.*;

// View단에 전달할 DTO
public interface CategoryNode {
    CategoryCode getCode();

    String getName();

    @JsonIgnore
    CategoryNode getParentCategory();

    @JsonManagedReference
    List<CategoryNode> getChildrenCategory();

    @JsonIgnore
    default List<CategoryNode> getAllParentCategory() { // 호출 카테고리를 포함한 모든 상위 카테고리륿 반환
        LinkedList<CategoryNode> allParent = new LinkedList<>();
        CategoryNode categoryNode = this;
        while(categoryNode != null) {
            allParent.addFirst(categoryNode);
            categoryNode = categoryNode.getParentCategory();
        }
        return allParent;
    }

    @JsonIgnore
    default boolean isRootCategory() {
        return getCode() == null || getCode().isRootCode();
    }

    @JsonIgnore
    default boolean isLeafCategory() {
        return CollectionUtils.isEmpty(getChildrenCategory());
    }

    default String toJson() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    static CategoryNode newRootNode(List<CategoryEntity> categoryNodes) {
        return new CategoryNodeSupporter() {
            @Override
            @JsonIgnore
            public CategoryCode getCode() {
                return null;
            }

            @Override
            @JsonIgnore
            public String getName() { return null; }

            @Override
            @JsonIgnore
            public CategoryEntity getParent() {
                return null;
            }

            @Override
            @JsonIgnore
            public List<CategoryEntity> getChildren() {
                return categoryNodes;
            }
        };
    }
}
