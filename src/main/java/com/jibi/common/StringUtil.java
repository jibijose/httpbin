package com.jibi.common;

import java.util.Random;

public class StringUtil {

    private static String ALPHASTRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
    private static int ALPHASTRINGLENGTH = ALPHASTRING.length();

    private StringUtil() {
    }

    public static String getRandomString(int length) {
        StringBuilder stringBuilder = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(ALPHASTRINGLENGTH);
            stringBuilder.append(ALPHASTRING.charAt(index));
        }
        return stringBuilder.toString();
    }
}
