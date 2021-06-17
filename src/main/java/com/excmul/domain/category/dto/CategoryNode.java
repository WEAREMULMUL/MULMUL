package com.excmul.domain.category.dto;

import com.excmul.domain.category.CategoryCode;

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
}
