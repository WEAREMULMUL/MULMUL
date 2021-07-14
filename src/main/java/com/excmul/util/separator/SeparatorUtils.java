package com.excmul.util.separator;

/*
 * :: Regex [enum]
 *
 * 중복되게 사용되는 구분자를 하나로 사용하게 된다면 편리할 것 같아서 Regex라는 class를 생성했습니다.
 *
 */

public enum SeparatorUtils {

    HYPHEN("-"),
    EMPTY(""),
    BLANK(" ");

    private final String separator;

    SeparatorUtils(String separator) {
        this.separator = separator;
    }
}
