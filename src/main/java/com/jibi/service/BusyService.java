package com.jibi.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/** The type Busy service. */
@Service
@Slf4j
public class BusyService {

  private static int SLOTS = 1000;
  private static int CORES = Runtime.getRuntime().availableProcessors();

  /**
   * Run in all processors.
   *
   * @param percentage the percentage
   * @param seconds the seconds
   */
  public void runInAllProcessors(int percentage, int seconds) {
    ExecutorService executor = Executors.newFixedThreadPool(CORES);
    log.debug("Running {} percentage for {} seconds in {} processors", percentage, seconds, CORES);
    for (int iCount = 0; iCount < CORES; iCount++) {
      executor.execute(new BusyProcessor(percentage, seconds));
    }
    executor.shutdown();
    try {
      executor.awaitTermination(seconds, TimeUnit.SECONDS);
    } catch (InterruptedException interruptedException) {
      log.warn("Processor threads not stopped", interruptedException);
    }
  }

  /**
   * Run in single processor.
   *
   * @param percentage the percentage
   * @param seconds the seconds
   */
  public void runInSingleProcessor(int percentage, int seconds) {
    new BusyProcessor(percentage, seconds).run();
  }

  /** The type Busy Processor. */
  private static class BusyProcessor implements Runnable {
    private int percentage;
    private int seconds;

    /**
     * Instantiates a new Busy processor.
     *
     * @param percentage the percentage
     * @param seconds the seconds
     */
    public BusyProcessor(int percentage, int seconds) {
      this.percentage = percentage;
      this.seconds = seconds;
    }

    /**
     * Run processor.
     *
     */
    @Override
    public void run() {
      long runSlotMillis = percentage * seconds * 1000 / SLOTS / 100;
      long idleSlotMillis = (100 - percentage) * seconds * 1000 / SLOTS / 100;

      for (int iCount = 0; iCount < SLOTS; iCount++) {
        long startTime = System.currentTimeMillis();
        try {
          long diffTime = System.currentTimeMillis() - startTime;
          while (diffTime < runSlotMillis) {
            diffTime = System.currentTimeMillis() - startTime;
          }
          Thread.sleep(idleSlotMillis);
        } catch (InterruptedException interruptedException) {
          log.warn("Thread is interrupted", interruptedException);
        }
      }
    }
  }
}
