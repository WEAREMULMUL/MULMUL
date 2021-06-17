package com.excmul.domain.category.dto;

import com.excmul.domain.category.CategoryCode;

import java.util.Collections;
import java.util.List;

// View단에 전달할 DTO
public interface CategoryNode {
    CategoryCode getCode();

    String getName();

    CategoryNode getParentCategory();

    List<CategoryNode> getChildCategory();

    default boolean isRootCategory() {
        return getCode().isRootCode();
    }

    static CategoryNode newRootNode(List<CategoryNode> categoryNodes) {
        return new CategoryNode() {
            @Override
            public CategoryCode getCode() {
                return null;
            }

            @Override
            public String getName() {
                return null;
            }

            @Override
            public CategoryNode getParentCategory() {
                return null;
            }

            @Override
            public List<CategoryNode> getChildCategory() {
                return Collections.unmodifiableList(categoryNodes);
            }
        };
    }
}
