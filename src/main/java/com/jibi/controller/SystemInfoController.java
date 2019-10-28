package com.jibi.controller;

import static com.jibi.common.Util.getFormattedSize;

import com.sun.management.OperatingSystemMXBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
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
        OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        Map<String, Object> systemInfoMap = new HashMap<>();

        systemInfoMap.put("cpu", getSystemCpuInfo(runtime, operatingSystemMXBean));
        systemInfoMap.put("os", getSystemOsInfo(runtime));
        systemInfoMap.put("memory", getSystemMemoryInfo(runtime));
        systemInfoMap.put("disk", getSystemDiskInfo(runtime));

        return systemInfoMap;
    }

    private double getCpuUsage() {
        OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        int availableProcessors = operatingSystemMXBean.getAvailableProcessors();
        long prevUpTime = runtimeMXBean.getUptime();
        long prevProcessCpuTime = operatingSystemMXBean.getProcessCpuTime();
        double cpuUsage;
        try {
            Thread.sleep(500);
        } catch (Exception ignored) {
        }

        operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        long upTime = runtimeMXBean.getUptime();
        long processCpuTime = operatingSystemMXBean.getProcessCpuTime();
        long elapsedCpu = processCpuTime - prevProcessCpuTime;
        long elapsedTime = upTime - prevUpTime;

        cpuUsage = Math.min(99F, elapsedCpu / (elapsedTime * 10000F * availableProcessors));
        return cpuUsage;
    }

    private Map<String, String> getSystemCpuInfo(Runtime runtime, OperatingSystemMXBean operatingSystemMXBean) {
        Map<String, String> systemInfoCpuMap = new HashMap<>();
        systemInfoCpuMap.put("processCpuLoad", Double.toString(operatingSystemMXBean.getProcessCpuLoad()));
        systemInfoCpuMap.put("processCpuTime", Double.toString(operatingSystemMXBean.getProcessCpuTime()));
        systemInfoCpuMap.put("systemCpuLoad", Double.toString(operatingSystemMXBean.getSystemCpuLoad()));
        systemInfoCpuMap.put("systemLoadAverage", Double.toString(operatingSystemMXBean.getSystemLoadAverage()));
        //systemInfoCpuMap.put("cpuUsage", Double.toString(getCpuUsage()));
        return systemInfoCpuMap;
    }

    private Map<String, String> getSystemDiskInfo(Runtime runtime) {
        Map<String, String> systemInfoDiskMap = new HashMap<>();
        File[] disks = File.listRoots();
        for (File file : disks) {
            String filePath = file.getPath();
            long freeSpace = file.getFreeSpace();
            systemInfoDiskMap.put("freeSpace" + filePath, Long.toString(freeSpace));
            systemInfoDiskMap.put("freeSpaceFormatted" + filePath, getFormattedSize(freeSpace));
            long usableSpace = file.getUsableSpace();
            systemInfoDiskMap.put("usableSpace" + filePath, Long.toString(usableSpace));
            systemInfoDiskMap.put("usableSpaceFormatted" + filePath, getFormattedSize(usableSpace));
            long totalSpace = file.getTotalSpace();
            systemInfoDiskMap.put("totalSpace" + filePath, Long.toString(totalSpace));
            systemInfoDiskMap.put("totalSpaceFormatted" + filePath, getFormattedSize(totalSpace));
        }
        return systemInfoDiskMap;
    }

    private Map<String, String> getSystemOsInfo(Runtime runtime) {
        Map<String, String> systemInfoOsMap = new HashMap<>();
        systemInfoOsMap.put("name", System.getProperty("os.name"));
        systemInfoOsMap.put("version", System.getProperty("os.version"));
        systemInfoOsMap.put("arch", System.getProperty("os.arch"));
        systemInfoOsMap.put("processors", Integer.toString(runtime.availableProcessors()));
        return systemInfoOsMap;
    }

    private Map<String, String> getSystemMemoryInfo(Runtime runtime) {
        Map<String, String> systemInfoMemoryMap = new HashMap<>();
        long freeMemory = runtime.freeMemory();
        systemInfoMemoryMap.put("freeBytes", Long.toString(freeMemory));
        systemInfoMemoryMap.put("freeFormatted", getFormattedSize(freeMemory));
        long allocatedMemory = runtime.totalMemory();
        systemInfoMemoryMap.put("allocatedBytes", Long.toString(allocatedMemory));
        systemInfoMemoryMap.put("allocatedFormatted", getFormattedSize(allocatedMemory));
        long maxMemory = runtime.maxMemory();
        systemInfoMemoryMap.put("maxBytes", Long.toString(maxMemory));
        systemInfoMemoryMap.put("maxFormatted", getFormattedSize(maxMemory));
        long usedMemory = allocatedMemory - freeMemory;
        systemInfoMemoryMap.put("usedBytes", Long.toString(usedMemory));
        systemInfoMemoryMap.put("usedFormatted", getFormattedSize(usedMemory));
        return systemInfoMemoryMap;
    }

}