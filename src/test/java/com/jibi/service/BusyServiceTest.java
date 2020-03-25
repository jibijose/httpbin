package com.jibi.service;

import net.jcip.annotations.ThreadSafe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
@ThreadSafe
public class BusyServiceTest {

  private BusyService busyService = new BusyService();

  @Test
  public void testRunInAllProcessors() {
    busyService.runInAllProcessors(10, 1);
  }

  @Test
  public void testRunInAllProcessorsInterrupt() {
    Thread.currentThread().interrupt();
    busyService.runInAllProcessors(10, 1);
  }

  @Test
  public void testRunInSingleProcessor() {
    busyService.runInSingleProcessor(10, 1);
  }

  @Test
  public void testRunInSingleProcessorInterrupt() {
    Thread.currentThread().interrupt();
    busyService.runInSingleProcessor(10, 1);
  }
}
