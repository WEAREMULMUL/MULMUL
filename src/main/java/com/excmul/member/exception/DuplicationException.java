package com.excmul.member.exception;

public class DuplicationException extends IllegalArgumentException {
    private ErrorCode errorCode;

    public DuplicationException(ErrorCode errorCode) {
        super(errorCode.value());

        this.errorCode = errorCode;
    }

    public enum ErrorCode {
        DUPLICATION_EMAIL("중복된 이메일 입니다."),
        DUPLICATION_NICKNAME("중복된 닉네임 입니다."),
        DUPLICATION_PHONE_NUMBER("중복된 전화번호 입니다.");

        private final String message;

        ErrorCode(final String message) {
            this.message = message;
        }

        public String value() {
            return message;
        }

    }
}