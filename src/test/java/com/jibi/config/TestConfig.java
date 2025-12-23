package com.jibi.config;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@Configuration
@Slf4j
public class TestConfig {

  @Bean
  public RestTemplate restTemplate() {
    RestTemplate restTemplate = new RestTemplate();

    restTemplate.setErrorHandler(
        new ResponseErrorHandler() {

          @Override
          public boolean hasError(ClientHttpResponse response) throws IOException {
            return false; // NEVER treat status as error
          }
        });
    return restTemplate;
  }
}
