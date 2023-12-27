package com.jibi.schedule;

import static org.awaitility.Awaitility.await;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

/** The type Schedulers test. */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(OutputCaptureExtension.class)
class SchedulersTest {
  @Autowired private Schedulers schedulers;

  /**
   * Test gc scheduling.
   *
   * @param output the output
   * @throws InterruptedException the interrupted exception
   */
  @Test
  void testGCScheduling(CapturedOutput output) throws InterruptedException {
    await()
        .atMost(Duration.ofMinutes(1L))
        .untilAsserted(() -> assertThat(output.getOut(), containsString("Invoking System GC")));
    await()
        .atMost(Duration.ofSeconds(5L))
        .untilAsserted(() -> assertThat(output.getOut(), containsString("Invoked System GC")));
    assertNotNull(output);
  }
}
