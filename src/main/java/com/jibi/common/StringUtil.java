package com.jibi.common;

public class StringUtil {

    private static String ALPHASTRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
    private static int ALPHASTRINGLENGTH = ALPHASTRING.length();

    private StringUtil() {
    }

    public static String getRandomString(int length) {
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = (int) (ALPHASTRINGLENGTH * Math.random());
            stringBuilder.append(ALPHASTRING.charAt(index));
        }
        return stringBuilder.toString();
    }
}
