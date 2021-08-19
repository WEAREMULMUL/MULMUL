package com.excmul.common.converter;


import com.excmul.common.domain.vo.TokenVo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TokenConverter implements Converter<String, TokenVo> {
    @Override
    public TokenVo convert(final String source) {
        return TokenVo.newInstance(source);
    }
}
