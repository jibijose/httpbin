package com.jibi.controller;

import com.jibi.service.BusyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CpuController {

    @Autowired
    private BusyService busyService;

    @RequestMapping(value = "/cpu/{percentage}/{time}", method = RequestMethod.GET)
    public void delay(@PathVariable("percentage") Integer percentage, @PathVariable("time") Integer time) {
        busyService.runInAllProcessors(percentage, time);
    }

}
