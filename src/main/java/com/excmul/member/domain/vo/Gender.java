package com.excmul.member.domain.vo;

public enum Gender {
    MAN("남자"),
    WOMAN("여자"),
    EMPTY("선택안함");

    private final String gender;

    Gender(final String gender) {
        this.gender = gender;
    }

    public String value() {
        return gender;
    }
}