package com.jibi.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.anyInt;
import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.doNothing;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Util.class)
@PowerMockIgnore({"com.sun.org.apache.xalan.*", "com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.*"})
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

    @Test(expected = Test.None.class)
    public void testRandomRange() {
        IntStream.rangeClosed(1, 100)
                .forEach(i -> {
                    int random = Util.randomNumber(3, 5);
                    assertThat(Util.randomNumber(3, 5), anyOf(is(3), is(4), is(5)));
                });
    }

    @Test(expected = Test.None.class)
    public void testFormattedSize() {
        assertEquals("Should return GB size", "100GB", Util.getFormattedSize(100l * 1024 * 1024 * 1024));
        assertEquals("Should return MB size", "100MB", Util.getFormattedSize(100l * 1024 * 1024));
        assertEquals("Should return KB size", "100KB", Util.getFormattedSize(100l * 1024));
        assertEquals("Should return B size", "100B", Util.getFormattedSize(100l));
    }
}
