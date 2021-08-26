package com.excmul.common.converter;

import com.excmul.common.domain.vo.Token;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TokenConverter implements Converter<String, Token> {
    @Override
    public Token convert(final String source) {
        return Token.newInstance(source);
    }
}
