package com.excmul.member.exception;

import org.springframework.security.authentication.AccountStatusException;

public class MemberLeftException extends AccountStatusException {
    private static final String ERROR_MESSAGE = "탈퇴한 계정 입니다.";

    public MemberLeftException() {
        super(ERROR_MESSAGE);
    }
}
