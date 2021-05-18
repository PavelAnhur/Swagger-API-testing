package com.epam.utils;

import java.security.SecureRandom;

public final class StringUtils {
    private static final String REGEXP = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%^&*()_+-=";
    private static final SecureRandom RANDOM = new SecureRandom();

    private StringUtils() {
    }

    public static String getRandomString(final int len) {
        StringBuilder builder = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            builder.append(REGEXP.charAt(RANDOM.nextInt(REGEXP.length())));
        }
        return builder.toString();
    }
}
