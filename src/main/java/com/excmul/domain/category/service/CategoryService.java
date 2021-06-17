package com.excmul.domain.category.service;

import com.excmul.domain.category.CategoryVO;
import com.excmul.domain.category.dto.CategoryNode;
import com.excmul.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional
    public void insertCategory(CategoryVO newChildCategory) {
        while (categoryRepository.existsByCode(newChildCategory.getCode()))
            newChildCategory.changeCode();
        categoryRepository.save(newChildCategory);
    }

    // TODO :: 여기에 캐시를 적용하면 좋을것 같습니다.
    @Transactional(readOnly = true)
    public CategoryNode findRootCategoryNode() { // 모든 카테고리 반환
        return CategoryNode.newRootNode(
                    categoryRepository.findAllWithGraph().stream()
                    .filter(CategoryNode::isRootCategory)
                    .collect(Collectors.toList()));
    }
}
