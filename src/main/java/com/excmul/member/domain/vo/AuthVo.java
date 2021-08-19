package com.excmul.member.domain.vo;

import java.util.Arrays;

// method -> value

public enum AuthVo {

    KAKAO("카카오"),
    GOOGLE("구글"),
    NAVER("네이버"),
    FACEBOOK("페이스북"),
    BASIC("기본"); // basic으로 바꾸기

    private final String auth;

    AuthVo(final String auth) {
        this.auth = auth;
    }

    public String value() {
        return auth;
    }

    public static AuthVo findByAuth(String inputAuth) {
        return Arrays.stream(AuthVo.values())
                .filter(b -> b.auth.equals(inputAuth))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 입력입니다."));
    }
}