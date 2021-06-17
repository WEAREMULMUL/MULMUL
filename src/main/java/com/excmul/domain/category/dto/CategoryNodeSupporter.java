package com.excmul.domain.category.dto;

import com.excmul.domain.category.CategoryCode;
import com.excmul.domain.category.CategoryVO;

import java.util.ArrayList;
import java.util.List;

// Category Entity 에서 CategoryNode를 대신 구현해주는 클래스
public interface CategoryNodeSupporter extends CategoryNode {
    CategoryCode getCode();

    String getName();

    CategoryVO getParent();

    List<CategoryVO> getChildren();

    default CategoryNode getParentCategory() {
        return getParent();
    }

    default List<CategoryNode> getChildCategory() {
        return new ArrayList<>(getChildren());
    }
}
