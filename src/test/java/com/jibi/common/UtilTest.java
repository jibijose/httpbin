package com.jibi.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(BlockJUnit4ClassRunner.class)
public class UtilTest {

    @Test(expected = Test.None.class)
    public void testSleepMillis() {
        Util.sleepMillis(10);
    }

    @Test(expected = Test.None.class)
    public void testSleepMillisSilent() {
        Util.sleepMillisSilent(10);
    }

    @Test(expected = Test.None.class)
    public void testSleepSeconds() {
        Util.sleepSeconds(2);
    }

    @Test(expected = Test.None.class)
    public void testSleepSecondsSilent() {
        Util.sleepSecondsSilent(2);
    }

    @Test(expected = Test.None.class)
    public void testSleepMinutes() {
        Util.sleepMinutes(1);
    }

    @Test(expected = Test.None.class)
    public void testSleepMinutesSilent() {
        Util.sleepMinutesSilent(1);
    }

}
