package com.excmul.domain.user.vo;

import lombok.Getter;

import java.util.Arrays;

/*
 * :: Auth [enum]
 *
 *
 *
 *
 *
 *
 */

@Getter
public enum Auth {

    KAKAO("카카오"),
    GOOGLE("구글"),
    NAVER("네이버"),
    FACEBOOK("페이스북"),
    DEFAULT("기본");

    // 작업 진행중
    private final String auth;

    Auth(final String auth) {
        this.auth = auth;
    }

    public static Auth findByAuth(String input) {
        return Arrays.stream(Auth.values())
                .filter(a -> a.auth.equals(input))
                .findAny()
                .orElse(DEFAULT);
    }
}