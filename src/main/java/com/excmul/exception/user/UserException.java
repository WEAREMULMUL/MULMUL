package com.excmul.exception.user;

/*
 * :: UserException [class]
 *
 * User에서 발생하는 모든 Excetion을 해당 클래스를 통해 관리
 *
 */

public class UserException extends IllegalArgumentException {
    public UserException(UserExceptionMessage exceptionMessage) {
        super(exceptionMessage.getValue());
    }
}
