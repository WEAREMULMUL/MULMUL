package com.excmul.util;

import java.util.Random;

public class StringUtils {
    private static final Random random = new Random();
    private static final char[] CHAR_MAP = "1234567890QWERTYUIOPASDFGHJKLZXCVBNM".toCharArray();

    public static String randomString(int codeSize) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < codeSize; i++) {
            builder.append(CHAR_MAP[random.nextInt(CHAR_MAP.length)]);
        }
        return builder.toString();
    }
}
