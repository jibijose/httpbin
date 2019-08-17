package com.jibi.controller;

import com.jibi.common.StringUtil;
import com.jibi.common.Util;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api(value = "Memory Api")
public class MemoryController {

    @RequestMapping(value = "/memory/{bytes}/{time}", method = RequestMethod.GET)
    public void memory(@PathVariable("bytes") Integer bytes, @PathVariable("time") Integer time) {

        String storeString = StringUtil.getRandomString(bytes / 2);
        long startTime = System.currentTimeMillis();
        Util.sleepMillisSilent(time * 1000);
        log.trace("Kept string of length {} in memory for {} seconds", storeString.length(), time);
    }
}
