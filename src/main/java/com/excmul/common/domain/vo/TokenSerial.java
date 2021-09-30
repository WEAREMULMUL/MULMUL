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

@Embeddable
@EqualsAndHashCode
public class TokenSerial {
    @Column(name = "TOKEN_TOKEN_SERIAL", nullable = false)
    private String tokenSerial;

    protected TokenSerial() {

    }

    private TokenSerial(String tokenSerial) {
        this.tokenSerial = tokenSerial;
    }

    public static TokenSerial newInstance(String token) {
        return new TokenSerial(token);
    }

    public static TokenSerial newRandomInstance(int length) {
        String tokenValue = newTokenValue(length);

        return new TokenSerial(tokenValue);
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
        return tokenSerial;
    }

    @RequiredArgsConstructor
    private enum Text {
        HYPHEN("-"),
        EMPTY("");

        private final String value;
    }
}
