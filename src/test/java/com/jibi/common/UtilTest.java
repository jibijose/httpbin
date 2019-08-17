package com.jibi.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.anyInt;
import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.doNothing;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Util.class)
public class UtilTest {

    @Test(expected = Test.None.class)
    public void testSleepMillis() {
        Util.sleepMillis(10);
    }

    @Test(expected = Test.None.class)
    public void testSleepMillisSilent() {
        Util.sleepMillisSilent(10);
    }

    @Test(expected = RuntimeException.class)
    public void testSleepMillisSilentInterruption() {
        Thread.currentThread().interrupt();
        Util.sleepMillisSilent(10);
    }

    @Test(expected = Test.None.class)
    public void testSleepSeconds() {
        Util.sleepSeconds(1);
    }

    @Test(expected = Test.None.class)
    public void testSleepSecondsSilent() {
        Util.sleepSecondsSilent(1);
    }

    @Test(expected = Test.None.class)
    public void testSleepMinutes() throws Exception {
        PowerMockito.spy(Util.class);
        doNothing().when(Util.class, "sleepSecondsSilent", anyInt());
        Util.sleepMinutes(1);
    }

    @Test(expected = Test.None.class)
    public void testSleepMinutesSilent() throws Exception {
        PowerMockito.spy(Util.class);
        doNothing().when(Util.class, "sleepSecondsSilent", anyInt());
        Util.sleepMinutesSilent(1);
    }

    @Test(expected = Test.None.class)
    public void testCheckNullOrBlankForNullString() {
        assertEquals("Should return true for null string", true, Util.checkNullOrBlank(null));
    }

    @Test(expected = Test.None.class)
    public void testCheckNullOrBlankForEmptyString() {
        assertEquals("Should return true for null string", true, Util.checkNullOrBlank(""));
    }

    @Test(expected = Test.None.class)
    public void testCheckNullOrBlankForSpacesString() {
        assertEquals("Should return true for null string", true, Util.checkNullOrBlank("   "));
    }

    @Test(expected = Test.None.class)
    public void testCheckNullOrBlankForString() {
        assertEquals("Should return true for null string", false, Util.checkNullOrBlank("anystring"));
    }

    @Test(expected = Test.None.class)
    public void testCheckNullOrBlankForStringWithSpaces() {
        assertEquals("Should return true for null string", false, Util.checkNullOrBlank("any string"));
    }

}
