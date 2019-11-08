package com.jibi.controller;

import com.jibi.common.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Disk Api")
@RestController(value = "Disk Api")
@RequestMapping("/disk")
@Slf4j
public class DiskController {

  private static volatile byte[] BYTES1MB = null;

  @ApiOperation(value = "Disk write api", response = Void.class)
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")
    }
  )
  @RequestMapping(
    value = "/write/{unit}/{count}",
    method = RequestMethod.GET,
    produces = {MediaType.TEXT_PLAIN_VALUE}
  )
  public void write(@PathVariable("unit") String unit, @PathVariable("count") Integer count)
      throws IOException {
    if (!"GB".equals(unit) && !"MB".equals(unit)) {
      throw new RuntimeException("Unit must be MB or GB");
    }
    if ("GB".equals(unit)) {
      count = count * 1024;
    }
    byte[] bytes = getCachedBytes1MB();
    IntStream.range(0, count)
        .forEach(
            index -> {
              try {
                writeAndDeleteTempFile(bytes);
              } catch (IOException ioException) {
                throw new RuntimeException("Write failed", ioException);
              }
            });
  }

  @ApiOperation(value = "Disk read api", response = Void.class)
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")
    }
  )
  @RequestMapping(
    value = "/read/{unit}/{count}",
    method = RequestMethod.GET,
    produces = {MediaType.TEXT_PLAIN_VALUE}
  )
  public void read(@PathVariable("unit") String unit, @PathVariable("count") Integer count) {
    if (!"GB".equals(unit) && !"MB".equals(unit)) {
      throw new RuntimeException("Unit must be MB or GB");
    }
    if ("GB".equals(unit)) {
      count = count * 1024;
    }

    IntStream.range(0, count)
        .forEach(
            index -> {
              try {
                byte[] bytes = getFileContent("1MB");
              } catch (IOException ioException) {
                throw new RuntimeException("Read failed", ioException);
              }
            });
  }

  private byte[] getCachedBytes1MB() throws IOException {
    if (BYTES1MB == null) {
      synchronized (this) {
        BYTES1MB = getFileContent("1MB");
      }
    }
    return BYTES1MB;
  }

  private byte[] getFileContent(String size) throws IOException {
    try (InputStream in = getClass().getResourceAsStream("/file/other/txt/" + size + ".txt")) {
      byte[] fileData = IOUtils.toByteArray(in);
      return fileData;
    }
  }

  private void writeAndDeleteTempFile(byte[] bytes) throws IOException {
    File file = null;
    try (InputStream inputStream = getClass().getResourceAsStream("/file/other/txt/1MB.txt")) {
      file = new File("temp" + Util.randomNumber(0, Integer.MAX_VALUE) + ".txt");
      FileUtils.writeByteArrayToFile(file, bytes);
    } finally {
      if (file != null) {
        boolean deleteStatus = file.delete();
        log.trace("File {} delete status is {}", file, deleteStatus);
      }
    }
  }
}
