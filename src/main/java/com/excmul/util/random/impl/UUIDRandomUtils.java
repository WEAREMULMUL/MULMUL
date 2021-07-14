package com.excmul.util.random.impl;

import com.excmul.util.random.RandomUtils;
import com.excmul.util.separator.SeparatorUtils;

import java.util.UUID;

/*
 * :: UUIDUtils [class]
 *
 */

public class UUIDRandomUtils implements RandomUtils {

    @Override
    public String getValue() {
        return uuid();
    }

    private String uuid() {
        return separate(UUID.randomUUID().toString());
    }

    private String separate(String input) {
        return input.replace(SeparatorUtils.HYPHEN.toString(), SeparatorUtils.EMPTY.toString());
    }
}
