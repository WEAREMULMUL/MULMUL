package com.excmul.category.domain.converter;

import com.excmul.category.application.CategoryService;
import com.excmul.category.domain.CategoryCode;
import com.excmul.category.domain.CategoryNode;
import com.excmul.common.domain.converter.SimpleConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@Component
public class CategoryNodeConverter implements SimpleConverter<String, CategoryNode> {
    private final CategoryService categoryService;

    @Override
    public CategoryNode asObject(String source) {
        CategoryCode categoryCode = CategoryCode.newInstance(source);
        return categoryService.findCategoryNodeByCode(categoryCode);
    }

    @Override
    public boolean validation(String source) {
        return StringUtils.hasText(source) && source.length() >= 2;
    }
}
