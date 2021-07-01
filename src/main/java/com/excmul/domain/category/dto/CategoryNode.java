package com.excmul.domain.category.dto;

import com.excmul.domain.category.CategoryCode;
import com.excmul.domain.category.CategoryVO;
import org.springframework.util.CollectionUtils;

import java.util.*;

// View단에 전달할 DTO
public interface CategoryNode {
    CategoryCode getCode();

    String getName();

    CategoryNode getParentCategory();

    List<CategoryNode> getChildrenCategory();

    default List<CategoryNode> getAllParentCategory() { // 호출 카테고리를 포함한 모든 상위 카테고리륿 반환
        LinkedList<CategoryNode> allParent = new LinkedList<>();
        CategoryNode categoryNode = this;
        while(categoryNode != null) {
            allParent.addFirst(categoryNode);
            categoryNode = categoryNode.getParentCategory();
        }
        return allParent;
    }

    default boolean isRootCategory() {
        return getCode() == null || getCode().isRootCode();
    }

    default boolean isLeafCategory() {
        return CollectionUtils.isEmpty(getChildrenCategory());
    }


    static CategoryNode newRootNode(List<CategoryVO> categoryNodes) {
        return new CategoryNodeSupporter() {
            @Override
            public CategoryCode getCode() {
                return null;
            }

            @Override
            public String getName() { return null; }

            @Override
            public CategoryVO getParent() {
                return null;
            }

            @Override
            public List<CategoryVO> getChildren() {
                return categoryNodes;
            }
        };
    }
}
