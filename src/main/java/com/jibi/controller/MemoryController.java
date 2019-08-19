package com.jibi.controller;

import com.jibi.common.StringUtil;
import com.jibi.common.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api(value = "Memory Api")
public class MemoryController {

    @ApiOperation(value = "Memory hold operation", response = String.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/memory/{bytes}/{time}", method = RequestMethod.GET)
    public void memoryHold(@PathVariable("bytes") Integer bytes, @PathVariable("time") Integer time) {

        String storeString = StringUtil.getRandomString(bytes / 2);
        long startTime = System.currentTimeMillis();
        Util.sleepMillisSilent(time * 1000);
        log.trace("Kept string of length {} in memory for {} seconds", storeString.length(), time);
    }
}
