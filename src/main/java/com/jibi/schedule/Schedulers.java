package com.jibi.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/** The type Schedulers. */
@Component
@Slf4j
public class Schedulers {

  /** Perform gc schedule. */
  @Scheduled(cron = "${schedule.gc}")
  public void performGCSchedule() {
    log.debug("Invoking System GC");
    System.gc();
    log.info("Invoked System GC");
  }
}
