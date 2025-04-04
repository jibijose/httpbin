package com.jibi.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** The type Swagger 2 config. */
@Configuration
@Slf4j
public class Swagger2Config {
  /**
   * Custom open api open api.
   *
   * @return the open api
   */
  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .components(new Components())
        .info(
            new Info()
                .title("Contact Application API")
                .description(
                    "This is a sample Spring Boot RESTful service using springdoc-openapi and OpenAPI 3."));
  }
}
