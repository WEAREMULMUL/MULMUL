package com.excmul.util;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public abstract class RandomUtils {
    private static Random getRandom() {
        return ThreadLocalRandom.current();
    }

    public static String randomString(int codeSize, char[] charPatterns) {
        Random random = getRandom();

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < codeSize; i++)
            builder.append(charPatterns[random.nextInt(charPatterns.length)]);
        return builder.toString();
    }
}
