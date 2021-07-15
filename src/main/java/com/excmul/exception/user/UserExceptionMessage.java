package com.excmul.exception.user;

/*
 * :: UserExceptionMessage [enum]
 *
 * UserException 발생시 필요한 메세지를 가지는 enum
 *
 */

import lombok.Getter;

// 클래스명을 바꾸기!!
@Getter
public enum UserExceptionMessage {
    USERNAME("이름 형식이 맞지 않습니다."),
    EMAIL("이메일 형식이 맞지 않습니다."),
    NICKNAME("닉네임 형식이 맞지 않습니다.");

    private final String value;

    UserExceptionMessage(String message) {
        this.value = message;
    }
}