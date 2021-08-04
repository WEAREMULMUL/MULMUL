package com.excmul.member.domain;

import javax.persistence.Column;

public enum Auth {

    KAKAO("카카오"),
    GOOGLE("구글"),
    NAVER("네이버"),
    FACEBOOK("페이스북"),
    DEFAULT("기본");

    @Column(name = "member_auth", nullable = false)
    private final String value;

    Auth(final String auth) {
        this.value = auth;
    }

    public String getValue() {
        return value;
    }
}