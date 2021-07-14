package com.excmul.domain.user.vo;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Gender {

    MAN("남자"),
    WOMAN("여자"),
    EMPTY("선택안함");

    // 작업 진행중
    private final String gender;

    Gender(final String gender) {
        this.gender = gender;
    }

    public static Gender findByGender(String input) {
        return Arrays.stream(Gender.values())
                .filter(g -> g.gender.equals(input))
                .findAny()
                .orElse(EMPTY);
    }
}