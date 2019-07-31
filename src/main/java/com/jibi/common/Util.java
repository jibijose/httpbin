package com.jibi.common;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Util {

    public static void sleepMillis(int milliSeconds) {
        log.debug("Sleeping " + milliSeconds + " milli seconds");
        sleepMillisSilent(milliSeconds);
        log.debug("Slept " + milliSeconds + " milli seconds");
    }

    public static void sleepMillisSilent(int milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException interruptedException) {
            log.error("Thread interrupted", interruptedException);
            throw new RuntimeException("Thread interrupted", interruptedException);
        }
    }

    public static void sleepSeconds(int seconds) {
        log.debug("Sleeping " + seconds + " seconds");
        sleepSecondsSilent(seconds);
        log.debug("Slept " + seconds + " seconds");
    }

    public static void sleepSecondsSilent(int seconds) {
        sleepMillisSilent(seconds * 1000);
    }

    public static void sleepMinutes(int minutes) {
        log.debug("Sleeping " + minutes + " minutes");
        sleepMinutesSilent(minutes);
        log.debug("Slept " + minutes + " minutes");
    }

    public static void sleepMinutesSilent(int minutes) {
        sleepSecondsSilent(minutes * 60);
    }

    public static boolean checkNullOrBlank(String str) {
        if (str == null) {
            return true;
        }
        if (str.trim().length() == 0) {
            return true;
        }
        return false;
    }
}
