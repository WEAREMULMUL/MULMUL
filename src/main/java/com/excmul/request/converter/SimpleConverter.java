package com.excmul.request.converter;

import com.excmul.domain.category.CategoryCode;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

public interface SimpleConverter<T, R> extends Converter<T, R> {
    R asObject(T source);

    default boolean validation(T source) {
        return true;
    }

    @Override
    default R convert(T source) {
        if (!validation(source))
            return null;
        return asObject(source);
    }
}
