package com.jibi.controller;

import com.jibi.common.Util;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api(value = "Delay Api")
public class DelayController {

    @RequestMapping(value = "/delay/{unit}/{time}", method = RequestMethod.GET)
    public void delay(@PathVariable("unit") String unit, @PathVariable("time") Integer time) {
        if ("seconds".equals(unit)) {
            Util.sleepSecondsSilent(time);
        } else if ("millis".equals(unit)) {
            Util.sleepMillisSilent(time);
        } else if ("minutes".equals(unit)) {
            Util.sleepMinutesSilent(time);
        } else {
            String errorMessage = String.format("Unknown time unit %s", unit);
            log.warn(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

}
