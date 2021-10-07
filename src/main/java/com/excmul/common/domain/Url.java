package com.excmul.common.domain;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor
public class Url {
    private static final char SLASH = '/';
    private static final int NUMBER_REGEX = 1;

    // TODO :: 이후에 외부 파일로 빼기 !! 회의에서 물어보기
    private static final Url SITE_URL = new Url("http://localhost:8080");

    private final String url;

    public static Url siteUrl() {
        return SITE_URL;
    }

    public String value() {
        return url;
    }

    public Url append(String url) {
        return new Url(attachSlash(this.url) + attachSlash(url));
    }

    private String attachSlash(String text) {
        if (lastChar() == SLASH) {
            return text;
        }
        return text + SLASH;
    }

    private char lastChar() {
        return url.charAt(url.length() - NUMBER_REGEX);
    }
}
