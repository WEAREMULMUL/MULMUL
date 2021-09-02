package com.excmul.common.converter;

import com.excmul.common.domain.vo.TokenSerial;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TokenConverter implements Converter<String, TokenSerial> {
    @Override
    public TokenSerial convert(final String source) {
        return TokenSerial.newInstance(source);
    }
}
