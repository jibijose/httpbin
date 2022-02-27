package com.jibi.schedule;

import static org.awaitility.Awaitility.await;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.Duration;

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

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
    @Autowired
    private Schedulers schedulers;

    @Test
    public void testGCScheduling() throws InterruptedException {
        systemOutRule.clearLog();
        await()
                .atMost(Duration.ofMinutes(1L))
                .untilAsserted(
                        () -> assertThat(systemOutRule.getLog(), containsString("Invoking System GC")));
        await()
                .atMost(Duration.ofSeconds(1L))
                .untilAsserted(
                        () -> assertThat(systemOutRule.getLog(), containsString("Invoked System GC")));
    }
}
