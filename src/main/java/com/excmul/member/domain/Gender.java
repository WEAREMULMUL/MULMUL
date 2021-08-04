package com.excmul.member.domain;

import javax.persistence.Column;

public enum Gender {

    MAN("남자"),
    WOMAN("여자"),
    EMPTY("선택안함");

    @Column(name = "member_gender", nullable = false)
    private final String value;

    Gender(final String gender) {
        this.value = gender;
    }

    public String getValue() {
        return value;
    }
}