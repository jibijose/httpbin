package com.jibi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Slf4j
public class HealthController {

    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public String health() {
        return "Server time " + new Date().toString();
    }
}