package com.jibi.common;

import java.text.NumberFormat;
import lombok.extern.slf4j.Slf4j;

/** The type Util. */
@Slf4j
public class Util {

  private static long KILOBYTES = 1024;
  private static long MEGABYTES = 1024 * 1024;
  private static long GIGABYTES = 1024 * 1024 * 1024;

  private Util() {}

  /**
   * Sleep millis.
   *
   * @param milliSeconds the milli seconds
   */
  public static void sleepMillis(int milliSeconds) {
    log.debug("Sleeping " + milliSeconds + " milli seconds");
    sleepMillisSilent(milliSeconds);
    log.debug("Slept " + milliSeconds + " milli seconds");
  }

  /**
   * Sleep millis silent.
   *
   * @param milliSeconds the milli seconds
   */
  public static void sleepMillisSilent(int milliSeconds) {
    try {
      Thread.sleep(milliSeconds);
    } catch (InterruptedException interruptedException) {
      log.error("Thread interrupted", interruptedException);
      // throw new RuntimeException("Thread interrupted", interruptedException);
    }
  }

  /**
   * Sleep seconds.
   *
   * @param seconds the seconds
   */
  public static void sleepSeconds(int seconds) {
    log.debug("Sleeping " + seconds + " seconds");
    sleepSecondsSilent(seconds);
    log.debug("Slept " + seconds + " seconds");
  }

  /**
   * Sleep seconds silent.
   *
   * @param seconds the seconds
   */
  public static void sleepSecondsSilent(int seconds) {
    sleepMillisSilent(seconds * 1000);
  }

  /**
   * Sleep minutes.
   *
   * @param minutes the minutes
   */
  public static void sleepMinutes(int minutes) {
    log.debug("Sleeping " + minutes + " minutes");
    sleepMinutesSilent(minutes);
    log.debug("Slept " + minutes + " minutes");
  }

  /**
   * Sleep minutes silent.
   *
   * @param minutes the minutes
   */
  public static void sleepMinutesSilent(int minutes) {
    sleepSecondsSilent(minutes * 60);
  }

  /**
   * Check null or blank boolean.
   *
   * @param str the str
   * @return the boolean
   */
  public static boolean checkNullOrBlank(String str) {
    if (str == null) {
      return true;
    }
    if (str.trim().length() == 0) {
      return true;
    }
    return false;
  }

  /**
   * Random number int.
   *
   * @param min the min
   * @param max the max
   * @return the int
   */
  public static int randomNumber(int min, int max) {
    return min + ((int) Math.round((Math.random()) * (max - min)));
  }

  /**
   * Gets formatted size.
   *
   * @param memory the memory
   * @return the formatted size
   */
  public static String getFormattedSize(long memory) {
    NumberFormat format = NumberFormat.getInstance();
    if (memory > GIGABYTES) {
      return format.format(memory / GIGABYTES) + "GB";
    }
    if (memory > MEGABYTES) {
      return format.format(memory / MEGABYTES) + "MB";
    }
    if (memory > KILOBYTES) {
      return format.format(memory / KILOBYTES) + "KB";
    }
    return format.format(memory) + "B";
  }
}
