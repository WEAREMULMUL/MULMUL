package com.excmul.member.domain.vo;

public enum AuthVo {

    KAKAO("카카오"),
    GOOGLE("구글"),
    NAVER("네이버"),
    FACEBOOK("페이스북"),
    DEFAULT("기본");

   private final String auth;

    AuthVo(final String auth) {
        this.auth = auth;
    }

    public String auth() {
        return auth;
    }
}