package com.jibi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Slf4j
@EnableScheduling
public class HttpBinApplication {
  public static void main(String[] args) {
    log.debug("Starting httpbin application");
    SpringApplication.run(HttpBinApplication.class, args);
    log.debug("Ending httpbin application");
  }

}
