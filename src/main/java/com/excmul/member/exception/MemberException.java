package com.excmul.member.exception;

public class MemberException extends IllegalArgumentException {
    public MemberException(MemberExceptionMessage exceptionMessage) {
        super(exceptionMessage.getValue());
    }
}
