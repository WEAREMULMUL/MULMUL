package com.excmul.util.random.impl;

/*
 * :: UUIDUtils [class]
 *
 * 싱글톤~~~
 */

import com.excmul.util.random.RandomUtils;
import com.excmul.util.separator.SeparatorUtils;

import java.util.UUID;

public class UUIDRandomUtils implements RandomUtils {

    @Override
    public String getValue() {
        return uuid();
    }

    private String uuid() {
        return separate(UUID.randomUUID().toString());
    }

    private String separate(String string) {
        return string.replace(SeparatorUtils.HYPHEN.toString(), SeparatorUtils.EMPTY.toString());
    }
}