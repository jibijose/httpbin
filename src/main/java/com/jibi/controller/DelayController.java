package com.jibi.controller;

import com.jibi.common.Util;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Delay Api")
@RestController(value = "Delay Api")
@RequestMapping("/delay")
@Slf4j
public class DelayController {

    @ApiOperation(value = "Delay constant api", response = Void.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/{unit}/{time}", method = RequestMethod.GET)
    public void delayConstant(@PathVariable("unit") String unit, @PathVariable("time") Integer time) {
        if ("millis".equals(unit)) {
            Util.sleepMillisSilent(time);
        } else if ("seconds".equals(unit)) {
            Util.sleepSecondsSilent(time);
        } else if ("minutes".equals(unit)) {
            Util.sleepMinutesSilent(time);
        } else {
            String errorMessage = String.format("Unknown time unit %s", unit);
            log.warn(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @ApiOperation(value = "Delay random api", response = Void.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/random/{unit}/{time}", method = RequestMethod.GET)
    public void delayRandom(@ApiParam(value = "Unit of time", allowableValues = "millis, seconds, minutes") @PathVariable("unit") String unit, @ApiParam(value = "Time") @PathVariable("time") Integer time) {
        delayRandomRange(unit, 0, time);
    }

    @ApiOperation(value = "Delay random range api", response = Void.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/random/{unit}/range/{minTime}/{maxTime}", method = RequestMethod.GET)
    public void delayRandomRange(@ApiParam(value = "Unit of time", allowableValues = "millis, seconds, minutes") @PathVariable("unit") String unit, @ApiParam(value = "Minimum time") @PathVariable("minTime") Integer minTime, @ApiParam(value = "Maximum time") @PathVariable("maxTime") Integer maxTime) {
        int time = Util.randomNumber(minTime, maxTime);
        if ("millis".equals(unit)) {
            Util.sleepMillisSilent(time);
        } else if ("seconds".equals(unit)) {
            Util.sleepSecondsSilent(time);
        } else if ("minutes".equals(unit)) {
            Util.sleepMinutesSilent(time);
        } else {
            String errorMessage = String.format("Unknown time unit %s", unit);
            log.warn(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

}
