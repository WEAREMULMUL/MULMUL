package com.excmul.request.converter;

import com.excmul.domain.category.CategoryCode;
import com.excmul.domain.category.CategoryVO;
import com.excmul.domain.category.dto.CategoryNode;
import com.excmul.domain.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class StringToCategoryNodeConverter implements SimpleConverter<String, CategoryNode> {
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
