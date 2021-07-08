package com.excmul.domain.category.dto;

import com.excmul.domain.category.CategoryCode;
import com.excmul.domain.category.CategoryVO;
import com.excmul.domain.category.dto.CategoryNode;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

// Category Entity 에서 CategoryNode를 대신 구현해주는 클래스
public interface CategoryNodeSupporter extends CategoryNode {
    CategoryCode getCode();

    String getName();

    CategoryVO getParent();

    List<CategoryVO> getChildren();

    @Override
    default CategoryNode getParentCategory() {
        return getParent();
    }

    @Override
    default List<CategoryNode> getChildrenCategory() {
        return CollectionUtils.isEmpty(getChildren()) ? Collections.emptyList() : Collections.unmodifiableList(getChildren());
    }
}
