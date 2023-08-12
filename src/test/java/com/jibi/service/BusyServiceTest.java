package com.jibi.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/** The type Busy service test. */
class BusyServiceTest {

  private BusyService busyService = new BusyService();

  /** Test run in all processors. */
  @Test
  void testRunInAllProcessors() {
    busyService.runInAllProcessors(10, 1);
    assertNotNull(busyService);
  }

  /** Test run in all processors interrupt. */
  @Test
  void testRunInAllProcessorsInterrupt() {
    Thread.currentThread().interrupt();
    busyService.runInAllProcessors(10, 1);
    assertNotNull(busyService);
  }

  /** Test run in single processor. */
  @Test
  void testRunInSingleProcessor() {
    busyService.runInSingleProcessor(10, 1);
    assertNotNull(busyService);
  }

  /** Test run in single processor interrupt. */
  @Test
  void testRunInSingleProcessorInterrupt() {
    Thread.currentThread().interrupt();
    busyService.runInSingleProcessor(10, 1);
    assertNotNull(busyService);
  }
}
