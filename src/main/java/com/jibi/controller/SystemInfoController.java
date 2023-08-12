package com.jibi.controller;

import static com.jibi.common.Util.getFormattedSize;

import com.jibi.model.SystemInfoModel;
import com.sun.management.OperatingSystemMXBean;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.File;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/** The type System info controller. */
@Tag(name = "System information Api", description = "System information Api")
@RestController(value = "System information Api")
@RequestMapping("/system/info")
@Slf4j
public class SystemInfoController {
  /**
   * System info system info model.
   *
   * @return the system info model
   */
  @Operation(
      summary = "System information api",
      description = "System information api",
      tags = {"system"})
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successful operation",
            content =
                @Content(
                    array =
                        @ArraySchema(schema = @Schema(implementation = SystemInfoModel.class)))),
        @ApiResponse(responseCode = "500", description = "Internal server error")
      })
  @RequestMapping(
      method = RequestMethod.GET,
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public SystemInfoModel systemInfo() {
    Runtime runtime = Runtime.getRuntime();
    OperatingSystemMXBean operatingSystemMXBean =
        (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    SystemInfoModel systemInfoModel = new SystemInfoModel();

    systemInfoModel.setCpu(getSystemCpuInfo(operatingSystemMXBean));
    systemInfoModel.setOs(getSystemOsInfo(runtime));
    systemInfoModel.setMemory(getSystemMemoryInfo(runtime));
    systemInfoModel.setDisks(getSystemDiskInfo());

    return systemInfoModel;
  }

  private SystemInfoModel.CpuInfoModel getSystemCpuInfo(
      OperatingSystemMXBean operatingSystemMXBean) {
    SystemInfoModel.CpuInfoModel cpuInfoModel = new SystemInfoModel.CpuInfoModel();
    cpuInfoModel.setProcessCpuLoad(Double.toString(operatingSystemMXBean.getProcessCpuLoad()));
    cpuInfoModel.setProcessCpuTime(Double.toString(operatingSystemMXBean.getProcessCpuTime()));
    cpuInfoModel.setSystemCpuLoad(Double.toString(operatingSystemMXBean.getSystemCpuLoad()));
    cpuInfoModel.setSystemLoadAverage(Double.toString(operatingSystemMXBean.getSystemCpuLoad()));
    return cpuInfoModel;
  }

  private SystemInfoModel.OsInfoModel getSystemOsInfo(Runtime runtime) {
    SystemInfoModel.OsInfoModel osInfoModel = new SystemInfoModel.OsInfoModel();
    osInfoModel.setName(System.getProperty("os.name"));
    osInfoModel.setVersion(System.getProperty("os.version"));
    osInfoModel.setArch(System.getProperty("os.arch"));
    osInfoModel.setProcessors(Integer.toString(runtime.availableProcessors()));
    return osInfoModel;
  }

  private SystemInfoModel.MemoryInfoModel getSystemMemoryInfo(Runtime runtime) {
    SystemInfoModel.MemoryInfoModel memoryInfoModel = new SystemInfoModel.MemoryInfoModel();
    long freeMemory = runtime.freeMemory();
    memoryInfoModel.setFreeBytes(Long.toString(freeMemory));
    memoryInfoModel.setFreeFormatted(getFormattedSize(freeMemory));
    long allocatedMemory = runtime.totalMemory();
    memoryInfoModel.setAllocatedBytes(Long.toString(allocatedMemory));
    memoryInfoModel.setAllocatedFormatted(getFormattedSize(allocatedMemory));
    long maxMemory = runtime.maxMemory();
    memoryInfoModel.setMaxBytes(Long.toString(maxMemory));
    memoryInfoModel.setMaxFormatted(getFormattedSize(maxMemory));
    long usedMemory = allocatedMemory - freeMemory;
    memoryInfoModel.setUsedBytes(Long.toString(usedMemory));
    memoryInfoModel.setUsedFormatted(getFormattedSize(usedMemory));
    return memoryInfoModel;
  }

  private Map<String, SystemInfoModel.DiskInfoModel> getSystemDiskInfo() {
    Map<String, SystemInfoModel.DiskInfoModel> diskModels = new HashMap<>();
    File[] disks = File.listRoots();
    for (File file : disks) {
      SystemInfoModel.DiskInfoModel diskInfoModel = new SystemInfoModel.DiskInfoModel();
      String filePath = file.getPath();

      long freeSpace = file.getFreeSpace();
      diskInfoModel.setFreeSpace(Long.toString(freeSpace));
      diskInfoModel.setFreeSpaceFormatted(getFormattedSize(freeSpace));
      long usableSpace = file.getUsableSpace();
      diskInfoModel.setUsableSpace(Long.toString(usableSpace));
      diskInfoModel.setUsableSpaceFormatted(getFormattedSize(usableSpace));
      long totalSpace = file.getTotalSpace();
      diskInfoModel.setTotalSpace(Long.toString(totalSpace));
      diskInfoModel.setTotalSpaceFormatted(getFormattedSize(totalSpace));

      log.info(filePath);
      filePath = filePath.replaceAll("/", "Fs");
      filePath = filePath.replaceAll(":", "Cn");
      filePath = filePath.replaceAll("=", "Eq");
      filePath = filePath.replaceAll("\\\\", "Bs");
      log.info(filePath);
      diskModels.put(filePath, diskInfoModel);
    }
    return diskModels;
  }
}
