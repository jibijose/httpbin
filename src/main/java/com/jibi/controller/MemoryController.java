package com.jibi.controller;

import com.jibi.common.StringUtil;
import com.jibi.common.Util;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Memory controller.
 */
@Tag(name = "Memory Api", description = "Memory Api")
@RestController(value = "Memory Api")
@RequestMapping("/memory")
@Slf4j
public class MemoryController {
    /**
     * Memory hold.
     *
     * @param bytes the bytes
     * @param time  the time
     */
    @Operation(
      summary = "Memory api",
      description = "Memory hold operation",
      tags = {"memory"})
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successful operation",
            content =
                @Content(array = @ArraySchema(schema = @Schema(implementation = String.class)))),
        @ApiResponse(responseCode = "500", description = "Internal server error")
      })
  @Parameter(name = "bytes", schema = @Schema(description = "Bytes", type = "integer"))
  @Parameter(name = "time", schema = @Schema(description = "Time in seconds", type = "integer"))
  @RequestMapping(value = "/{bytes}/{time}", method = RequestMethod.GET)
  public void memoryHold(@PathVariable("bytes") Integer bytes, @PathVariable("time") Integer time) {

    String storeString = StringUtil.getRandomString(bytes / 2);
    Util.sleepMillisSilent(time * 1000);
    log.trace("Kept string of length {} in memory for {} seconds", storeString.length(), time);
  }
}
