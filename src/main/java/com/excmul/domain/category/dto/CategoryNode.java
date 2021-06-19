package com.excmul.domain.category.dto;

import com.excmul.domain.category.CategoryCode;
import com.excmul.domain.category.CategoryVO;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

// View단에 전달할 DTO
public interface CategoryNode {
    CategoryCode getCode();

    String getName();

    CategoryNode getParentCategory();

    List<CategoryNode> getChildrenCategory();

    boolean isRootCategory();

    boolean isLeafCategory();

    static CategoryNode newRootNode(List<CategoryNode> categoryNodes) {
        return new CategoryNodeSupporter() {
            @Override
            public CategoryCode getCode() {
                return null;
            }

            @Override
            public String getName() {
                return null;
            }

            @Override
            public CategoryVO getParent() {
                return null;
            }

            @Override
            public List<CategoryVO> getChildren() {
                return null;
            }

            @Override
            public List<CategoryNode> getChildrenCategory() {
                return Collections.unmodifiableList(categoryNodes);
            }
        };
    }
}
