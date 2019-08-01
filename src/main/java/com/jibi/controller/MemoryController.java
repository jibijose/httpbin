package com.jibi.controller;

import com.jibi.common.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@RestController
@Slf4j
public class MemoryController {

    @RequestMapping(value = "/memory/{loop}/{time}", method = RequestMethod.GET)
    public void memory(@PathVariable("loop") Integer loop, @PathVariable("time") Integer time) {
        List<Integer> storeValues = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        long durationMillis = time * 1000;
        Random random = new Random(new Date().getTime());

        IntStream.rangeClosed(1, loop).forEach(i -> storeValues.add(random.nextInt()));

        long endTime = System.currentTimeMillis();
        if (endTime < (startTime + time * 1000)) {
            int sleepMillis = (int) ((startTime + time * 1000) - endTime);
            Util.sleepMillisSilent(sleepMillis);
        }
    }
}
