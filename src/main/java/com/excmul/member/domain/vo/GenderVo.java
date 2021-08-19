package com.excmul.member.domain.vo;

public enum GenderVo {

    MAN("남자"),
    WOMAN("여자"),
    EMPTY("선택안함");

    private final String gender;

    GenderVo(final String gender) {
        this.gender = gender;
    }

    public String value() {
        return gender;
    }
}