package com.jibi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@Slf4j
@EnableScheduling
public class HttpBinApplication {
  public static void main(String[] args) {
    log.debug("Starting httpbin application");
    SpringApplication.run(HttpBinApplication.class, args);
    log.debug("Started httpbin application");
  }

  @Scheduled(cron = "${schedule.gc}")
  public void performGCSchedule() throws Exception {
    log.debug("Invoking System GC");
    try {
      System.gc();
      log.info("Invoked System GC");
    } catch (Exception exception) {
      log.warn("Error System GC", exception);
    }
  }
}
