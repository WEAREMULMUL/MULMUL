package com.excmul.member.exception;

public enum MemberExceptionMessage {
    NAME("이름 형식이 맞지 않습니다."),
    EMAIL("이메일 형식이 맞지 않습니다."),
    NICKNAME("닉네임 형식이 맞지 않습니다."),
    PHONE_NUMBER("전화번호 형식이 맞지 않습니다."),
    PASSWORD("비밀번호는 최소 8자 ~ 최대 30자, 최소 영문 소문자, 대문자, 특수문자를 각각 하나 이상 포함해야 합니다."),
    BIRTH("생년월일은 yyyymmdd 형식에 맞추어 작성해주세요."),
    FAIL_SIGN("회원가입을 실패했습니다.");

    private final String value;

    MemberExceptionMessage(String message) {
        this.value = message;
    }

    public String getValue() {
        return value;
    }
}
