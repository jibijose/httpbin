package com.jibi.schedule;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SchedulersTest {

    @Autowired
    private Schedulers schedulers;

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void testGCScheduling() throws InterruptedException {
        systemOutRule.clearLog();
        Thread.sleep(60*1000);
        assertThat(systemOutRule.getLog(), containsString("Invoking System GC"));
        assertThat(systemOutRule.getLog(), containsString("Invoked System GC"));
    }
}
