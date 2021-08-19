package com.excmul.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class TokenException extends RuntimeException {
    public TokenException(ErrorCode errorCode) {
        super(errorCode.errorMessage);
    }

    @RequiredArgsConstructor
    public enum ErrorCode {
        NOT_FOUND("존재하지 않는 토큰 입니다."),
        OUT_OF_LENGTH("유효하지 않은 토큰의 길이 입니다."),
        ALREADY_USED("이미 사용된 토큰 입니다."),
        TIME_OUT("토큰의 제한 시간이 초과 되었습니다.");

        private final String errorMessage;
    }
}
