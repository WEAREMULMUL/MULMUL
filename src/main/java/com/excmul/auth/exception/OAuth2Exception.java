package com.excmul.auth.exception;

import lombok.RequiredArgsConstructor;

public class OAuth2Exception extends RuntimeException {
    private final ErrorCode errorCode;

    public OAuth2Exception(ErrorCode errorCode) {
        super(errorCode.message);

        this.errorCode = errorCode;
    }

    @RequiredArgsConstructor
    public enum ErrorCode {
        NOT_FOUND_SOCIAL_TYPE("지원하지 않는 소셜 로그인 입니다."),
        NOT_SOCIAL_ACCOUNT("소셜 계정이 아닙니다."),
        ALREADY_COMPLETED_SIGN_UP("가입이 이미 완료 되었습니다.");

        private final String message;

        public String message() {
            return message;
        }
    }
}