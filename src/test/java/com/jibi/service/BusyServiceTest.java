package com.jibi.service;

import org.junit.jupiter.api.Test;

/** The type Busy service test. */
public class BusyServiceTest {

  private BusyService busyService = new BusyService();

  /** Test run in all processors. */
  @Test
  public void testRunInAllProcessors() {
    busyService.runInAllProcessors(10, 1);
  }

  /** Test run in all processors interrupt. */
  @Test
  public void testRunInAllProcessorsInterrupt() {
    Thread.currentThread().interrupt();
    busyService.runInAllProcessors(10, 1);
  }

  /** Test run in single processor. */
  @Test
  public void testRunInSingleProcessor() {
    busyService.runInSingleProcessor(10, 1);
  }

  /** Test run in single processor interrupt. */
  @Test
  public void testRunInSingleProcessorInterrupt() {
    Thread.currentThread().interrupt();
    busyService.runInSingleProcessor(10, 1);
  }
}
