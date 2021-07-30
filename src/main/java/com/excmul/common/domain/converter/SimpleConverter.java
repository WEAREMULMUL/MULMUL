package com.excmul.common.domain.converter;

import org.springframework.core.convert.converter.Converter;

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
