package com.excmul.notice.exception;

public class NotFoundMemberException extends RuntimeException {
    private static final String ERROR_MESSAGE = "공지사항을 찾을 수 없습니다.";

    public NotFoundMemberException() {
        super(ERROR_MESSAGE);
    }
}
