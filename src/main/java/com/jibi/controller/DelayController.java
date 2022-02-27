package com.jibi.controller;

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

@Tag(name = "Delay Api", description = "Delay Api")
@RestController(value = "Delay Api")
@RequestMapping("/delay")
@Slf4j
public class DelayController {

  @Operation(
      summary = "Delay constant api",
      description = "Delay constant api",
      tags = {"disk"})
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successful operation",
            content =
                @Content(array = @ArraySchema(schema = @Schema(implementation = Void.class)))),
        @ApiResponse(responseCode = "500", description = "Internal server error")
      })
  @Parameter(
      name = "unit",
      schema =
          @Schema(
              description = "Unit",
              type = "string",
              allowableValues = {"MB", "GB"}))
  @Parameter(name = "time", schema = @Schema(description = "Time", type = "integer"))
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

  @Operation(
      summary = "Delay random api",
      description = "Delay random api",
      tags = {"disk"})
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successful operation",
            content =
                @Content(array = @ArraySchema(schema = @Schema(implementation = Void.class)))),
        @ApiResponse(responseCode = "500", description = "Internal server error")
      })
  @Parameter(
      name = "unit",
      schema =
          @Schema(
              description = "Unit of time",
              type = "string",
              allowableValues = {"millis", "seconds", "minutes"}))
  @Parameter(name = "time", schema = @Schema(description = "Time", type = "integer"))
  @RequestMapping(value = "/random/{unit}/{time}", method = RequestMethod.GET)
  public void delayRandom(@PathVariable String unit, @PathVariable Integer time) {
    delayRandomRange(unit, 0, time);
  }

  @Operation(
      summary = "Delay random range api",
      description = "Delay random range api",
      tags = {"disk"})
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successful operation",
            content =
                @Content(array = @ArraySchema(schema = @Schema(implementation = Void.class)))),
        @ApiResponse(responseCode = "500", description = "Internal server error")
      })
  @Parameter(
      name = "unit",
      schema =
          @Schema(
              description = "Unit of time",
              type = "string",
              allowableValues = {"millis", "seconds", "minutes"}))
  @Parameter(name = "minTime", schema = @Schema(description = "Minimum time", type = "integer"))
  @Parameter(name = "maxTime", schema = @Schema(description = "Maximum time", type = "integer"))
  @RequestMapping(value = "/random/{unit}/range/{minTime}/{maxTime}", method = RequestMethod.GET)
  public void delayRandomRange(
      @PathVariable String unit, @PathVariable Integer minTime, @PathVariable Integer maxTime) {
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
