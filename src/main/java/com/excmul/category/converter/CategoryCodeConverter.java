package com.excmul.category.converter;

import com.excmul.category.domain.CategoryCode;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryCodeConverter implements Converter<String, CategoryCode> {
    @Override
    public CategoryCode convert(String source) {
        return CategoryCode.newInstance(source);
    }
}
