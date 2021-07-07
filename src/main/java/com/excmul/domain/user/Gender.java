package com.excmul.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {

    MAN("GENDER_MAN", "남자"),
    WOMAN("GENDER_WOMAN", "여자"),
    EMPTY("GENDER_EMPTY", "선택안함");

    private final String key;
    private final String title;

    // 작업 진행중
}