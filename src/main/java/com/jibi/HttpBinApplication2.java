package com.jibi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class HttpBinApplication2 {
    public static void main(String[] args) {

        String filePath = "C:\\=";
        log.info(filePath);
        filePath = filePath.replaceAll("/", "Fs");
        log.info(filePath);
        filePath = filePath.replaceAll(":", "Cn");
        log.info(filePath);
        filePath = filePath.replaceAll("=", "Eq");
        log.info(filePath);
        filePath = filePath.replaceAll("\\\\", "Bs");
        log.info(filePath);
    }
}