package com.jibi.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

/** The type String util test. */
class StringUtilTest {

  /** Test get random string. */
  @Test
  void testGetRandomString() {
    assertEquals(10, StringUtil.getRandomString(10).length());
  }

  /** Test get different random strings. */
  @Test
  void testGetDifferentRandomStrings() {
    String randomString1 = StringUtil.getRandomString(10);
    String randomString2 = StringUtil.getRandomString(10);
    assertNotEquals("Should return different strings", randomString1, randomString2);
  }
}
