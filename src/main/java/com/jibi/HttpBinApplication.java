package com.jibi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class HttpBinApplication {
    public static void main(String[] args) {
        SpringApplication.run(HttpBinApplication.class, args);
    }
}