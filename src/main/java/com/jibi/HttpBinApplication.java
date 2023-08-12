package com.jibi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** The type Http bin application. */
@SpringBootApplication
@Slf4j
public class HttpBinApplication {
  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    log.debug("Starting httpbin application");
    SpringApplication.run(HttpBinApplication.class, args);
    log.debug("Started httpbin application");
  }
}
