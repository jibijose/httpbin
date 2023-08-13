package com.jibi.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/** The type Util test. */
public class UtilTest {

  /** Test sleep millis. */
  @Test
  void testSleepMillis() {
    Util.sleepMillis(10);
  }

  /** Test sleep millis silent. */
  @Test
  void testSleepMillisSilent() {
    Thread myRunThread = new Thread(() -> Util.sleepMillisSilent(10));
    myRunThread.start();
    myRunThread.interrupt();
  }

  /** Test sleep seconds. */
  @Test
  void testSleepSeconds() {
    Util.sleepSeconds(1);
  }

  /** Test sleep seconds silent. */
  @Test
  void testSleepSecondsSilent() {
    Thread myRunThread = new Thread(() -> Util.sleepSecondsSilent(1));
    myRunThread.start();
    myRunThread.interrupt();
  }

  /** Test sleep minutes. */
  @Test
  void testSleepMinutes() {
    Util.sleepMinutes(0);
  }

  /** Test sleep minutes silent. */
  @Test
  void testSleepMinutesSilent() {
    Thread myRunThread = new Thread(() -> Util.sleepMinutesSilent(1));
    myRunThread.start();
    myRunThread.interrupt();
  }

  /** Test check null or blank. */
  @Test
  void testCheckNullOrBlank() {
    assertTrue(Util.checkNullOrBlank(null));
    assertTrue(Util.checkNullOrBlank(""));
    assertFalse(Util.checkNullOrBlank("test"));
  }

  /** Test get formatted size. */
  @Test
  void testGetFormattedSize() {
    assertEquals("2GB", Util.getFormattedSize(2 * Util.GIGABYTES));
    assertEquals("2MB", Util.getFormattedSize(2 * Util.MEGABYTES));
    assertEquals("2KB", Util.getFormattedSize(2 * Util.KILOBYTES));
    assertEquals("2B", Util.getFormattedSize(2));
  }
}
