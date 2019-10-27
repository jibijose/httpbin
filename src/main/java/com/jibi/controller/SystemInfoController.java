package com.jibi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Api(value = "System information Api")
@RestController(value = "System information Api")
@RequestMapping("/system/info")
public class SystemInfoController {

    @ApiOperation(value = "System information api", response = Map.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> systemInfo() {
        Runtime runtime = Runtime.getRuntime();
        Map<String, Object> systemInfoMap = new HashMap<>();

        Map<String, String> systemInfoMemoryMap = new HashMap<>();
        long freeMemory = runtime.freeMemory();
        systemInfoMemoryMap.put("freeBytes", Long.toString(freeMemory));
        systemInfoMemoryMap.put("freeFormatted", getFormattedMemory(freeMemory));
        systemInfoMap.put("memory", systemInfoMemoryMap);

        return systemInfoMap;
    }

    private static long KILOBYTES = 1024;
    private static long MEGABYTES = 1024 * 1024;
    private static long GIGABYTES = 1024 * 1024 * 1024;

    private String getFormattedMemory(long memory) {
        NumberFormat format = NumberFormat.getInstance();
        if (memory > GIGABYTES) {
            return format.format(memory / GIGABYTES) + "GB";
        }
        if (memory > MEGABYTES) {
            return format.format(memory / MEGABYTES) + "MB";
        }
        if (memory > KILOBYTES) {
            return format.format(memory / KILOBYTES) + "KB";
        }
        return format.format(memory) + "B";
    }
}