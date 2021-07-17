package com.excmul.domain.user.vo;


import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;


class UsernameTest {

    @Autowired
    private Username username;

    @DisplayName("사용자_이름_유효성_검사_성공")
    @ParameterizedTest
    @CsvSource({
            "김동건",
            "김재원",
            "이희찬",
            "김동건이",
            "행복주택"
    })
    void validate_username_success(String input) {
        username = new Username(input);
    }

    @DisplayName("사용자_이름_유효성_검사_성공_실패")
    @ParameterizedTest
    @CsvSource({
            "김동건A",
            "김WRXC1",
            "이123",
            "",
            "이a동",
            "행복!"
    })
    void validate_username_fail(String input) {
        username = new Username(input);
    }

}