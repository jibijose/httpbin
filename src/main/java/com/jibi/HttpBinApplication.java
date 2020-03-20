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
    log.debug("Ending httpbin application");
  }

  @Scheduled(cron = "0 */1 * * * ?")
  public void perform() throws Exception {
    log.error("Invoking System GC");
    try {
      // System.gc();
      log.info("Invoked System GC");
    } catch (Exception exception) {
      log.warn("Errored System GC", exception);
    }
  }
}
