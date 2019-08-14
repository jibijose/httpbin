package com.jibi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.HttpMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Api(value = "Health endpoints")
public class HealthController {

    @ApiOperation(value = "Health api", response = String.class)
    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public String health() {
        return "Server time " + new Date().toString();
    }
}