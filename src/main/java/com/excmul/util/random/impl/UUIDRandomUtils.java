package com.excmul.util.random.impl;

import com.excmul.util.random.RandomUtils;
import com.excmul.util.separator.SeparatorUtils;

import java.util.UUID;

/*
 * :: UUIDUtils [class]
 *
 * 클래스 로딩시점부터 시작
 *
 * LazyHolder, Bill Pugh Singleton
 *
 */

public class UUIDRandomUtils implements RandomUtils {

    private UUIDRandomUtils() {
    }
    
    public static UUIDRandomUtils getInstance() {
        return InnerStaticClass.Instance;
    }

    private static class InnerStaticClass {
        private static final UUIDRandomUtils Instance = new UUIDRandomUtils();
    }

    public String getValue() {
        return getInstance().uuid();
    }

    private String uuid() {
        return separate(UUID.randomUUID().toString());
    }

    private String separate(String string) {
        return string.replace(SeparatorUtils.HYPHEN.toString(), SeparatorUtils.EMPTY.toString());
    }
}
