package com.excmul.follow.exception;

import lombok.RequiredArgsConstructor;

public class FollowException extends RuntimeException {
    public FollowException(FollowException.ErrorCode errorCode) {
        super(errorCode.errorMessage);
    }

    @RequiredArgsConstructor
    public enum ErrorCode {
        IS_SAME_MEMBER("동일 계정입니다.");

        private final String errorMessage;
    }
}
