package com.excmul.domain.category.service;

import com.excmul.domain.category.CategoryCode;
import com.excmul.domain.category.CategoryVO;
import com.excmul.domain.category.dto.CategoryNode;
import com.excmul.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public boolean existCategory(CategoryCode categoryCode) {
        return categoryRepository.existsByCode(categoryCode);
    }

    @Transactional
    public void insertCategory(CategoryVO newCategory) {
        while (existCategory(newCategory.getCode()))
            newCategory.changeCode();
        categoryRepository.save(newCategory);
    }

    // TODO :: 여기에 캐시를 적용하면 좋을것 같습니다.
    // maxLevel 0 (루트 카테고리만 불러올 경우) 상위 카테고리와 하위 카테고리를 불러오지 않습니다.
    // maxLevel >= 1 상위 카테고리와 하위 카테고리를 함꼐 불러옵니다.
    @Transactional(readOnly = true)
    public CategoryNode findCategoryByLevel(int maxLevel) {
        if (maxLevel == 0) {
            return CategoryNode.newRootNode(
                    categoryRepository.findAllByLevel(1));
        }
        return CategoryNode.newRootNode(
                    categoryRepository.findByLevelWithGraph(maxLevel).stream()
                    .filter(CategoryNode::isRootCategory)
                    .collect(Collectors.toList()));
    }

    @Transactional(readOnly = true)
    public CategoryNode findCategoryNodeByCode(CategoryCode categoryCode) {
        return categoryRepository.findByCodeWithGraph(categoryCode);
    }
}
