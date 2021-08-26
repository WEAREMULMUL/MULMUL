package com.excmul.member.exception;

import lombok.Getter;

@Getter
public class InvalidInputException extends IllegalArgumentException {

    private ErrorCode errorCode;

    public InvalidInputException(ErrorCode errorCode) {
        super(errorCode.value());

        this.errorCode = errorCode;
    }

    public enum ErrorCode {
        NAME("이름 형식이 맞지 않습니다."),
        EMAIL("이메일 형식이 맞지 않습니다."),
        NICKNAME("닉네임 형식이 맞지 않습니다."),
        PHONE_NUMBER("전화번호 형식이 맞지 않습니다."),
        PASSWORD("비밀번호는 최소 8자 ~ 최대 30자, 최소 영문 소문자, 대문자, 특수문자를 각각 하나 이상 포함해야 합니다."),
        BIRTH("생년월일은 yyyymmdd 형식에 맞추어 작성해주세요."),
        OLD_PASSWORD_MISMATCH("입력하신 패스워드가 일차하지 않습니다."),
        NEW_PASSWORD_MISMATCH("입력하신 새로운 패스워드가 동일하지 않습니다."),
        IS_SAME_PASSWORD("입력하신 패스워드가 기존 패스워드와 동일합니다.");

        private final String message;

        ErrorCode(final String message) {
            this.message = message;
        }

        public String value() {
            return message;
        }
    }
}