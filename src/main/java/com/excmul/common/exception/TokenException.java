package com.excmul.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class TokenException extends RuntimeException {
    public TokenException(ErrorCode errorCode) {
        super(errorCode.errorMessage);
    }

    @RequiredArgsConstructor
    public enum ErrorCode {
        OUT_OF_TOKEN_LENGTH("유효하지 않은 토큰의 길이 입니다.");

        private final String errorMessage;
    }
}
