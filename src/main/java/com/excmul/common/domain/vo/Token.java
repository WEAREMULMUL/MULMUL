package com.excmul.common.domain.vo;

import com.excmul.common.exception.TokenException;
import com.excmul.common.exception.TokenException.ErrorCode;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.UUID;

@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Token {
    @Column(name = "TOKEN", nullable = false)
    private String token;

    private Token(String token) {
        this.token = token;
    }

    public static Token newInstance(String token) {
        return new Token(token);
    }

    public static Token newRandomInstance(int length) {
        String tokenValue = newTokenValue(length);

        return new Token(tokenValue);
    }

    private static String newTokenValue(int length) {
        String uuid = UUID.randomUUID().toString().replaceAll(Text.HYPHEN.value, Text.EMPTY.value);

        validateTokenLength(uuid, length);
        return uuid.substring(0, length);
    }

    private static void validateTokenLength(String token, int length) {
        if (token.length() < length) {
            throw new TokenException(ErrorCode.OUT_OF_LENGTH);
        }
    }

    public String value() {
        return token;
    }

    @RequiredArgsConstructor
    private enum Text {
        HYPHEN("-"),
        EMPTY("");

        private final String value;
    }
}
