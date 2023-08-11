package com.jibi.common;

import java.util.Date;
import java.util.Random;

/** The type String util. */
public class StringUtil {

  private static String ALPHASTRING =
      "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
  private static int ALPHASTRINGLENGTH = ALPHASTRING.length();

  private static final Random random = new Random(new Date().getTime());

  private StringUtil() {}

  /**
   * Gets random string.
   *
   * @param length the length
   * @return the random string
   */
  public static String getRandomString(int length) {
    StringBuilder stringBuilder = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      int index = random.nextInt(ALPHASTRINGLENGTH);
      stringBuilder.append(ALPHASTRING.charAt(index));
    }
    return stringBuilder.toString();
  }
}
