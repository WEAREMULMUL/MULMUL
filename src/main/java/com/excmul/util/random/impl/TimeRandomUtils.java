package com.excmul.util.random.impl;

import com.excmul.util.random.RandomUtils;

import java.text.SimpleDateFormat;
import java.util.Random;

/*
 * :: DateRandomUtils [class]
 *
 *
 *
 */

public class TimeRandomUtils implements RandomUtils {

    private static final String DATE_FORMAT = "yyyyMMddHHmmss";
    private static final int INIT_LIMIT = 97;
    private static final int FINAL_LIMIT = 123;
    private static final int RANDOM_STRING_SIZE = 5;

    @Override
    public String getValue() {
        return date() + randomString();
    }

    private String date() {
        return new SimpleDateFormat(DATE_FORMAT).format(System.currentTimeMillis());
    }

    private String randomString() {
        Random random = new Random();
        return random.ints(INIT_LIMIT, FINAL_LIMIT)
                .limit(RANDOM_STRING_SIZE)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
