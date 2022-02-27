package com.jibi.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class StringUtilTest {

    @Test
    public void testGetRandomString() {
        assertEquals(
                "Should return random string of expected length",
                10,
                StringUtil.getRandomString(10).length());
    }

    @Test
    public void testGetDifferentRandomStrings() {
        String randomString1 = StringUtil.getRandomString(10);
        String randomString2 = StringUtil.getRandomString(10);
        assertNotEquals("Should return different strings", randomString1, randomString2);
    }
}
