package com.excmul.notice.exception;

public class InvalidInputException extends IllegalArgumentException {

    private ErrorCode errorCode;

    public InvalidInputException(ErrorCode errorCode) {
        super(errorCode.value());
        this.errorCode = errorCode;
    }

    public enum ErrorCode {
        TITLE("공지사항 제목 형식에 맞지 않습니다.");
        private final String message;

        ErrorCode(final String message) {
            this.message = message;
        }

        public String value() {
            return message;
        }
    }
}