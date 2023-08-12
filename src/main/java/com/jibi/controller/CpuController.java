package com.jibi.controller;

import com.jibi.service.BusyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/** The type Cpu controller. */
@Tag(name = "Cpu Api", description = "Cpu Api")
@RestController(value = "Cpu Api")
@RequestMapping("/cpu")
public class CpuController {

  @Autowired private BusyService busyService;

  /**
   * Cpu all processors.
   *
   * @param percentage the percentage
   * @param time the time
   */
  @Operation(
      summary = "Cpu all processors operation",
      description = "Cpu all processors operation",
      tags = {"disk"})
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successful operation",
            content =
                @Content(array = @ArraySchema(schema = @Schema(implementation = String.class)))),
        @ApiResponse(responseCode = "500", description = "Internal server error")
      })
  @RequestMapping(value = "/all/{percentage}/{time}", method = RequestMethod.GET)
  public void cpuAllProcessors(
      @PathVariable("percentage") Integer percentage, @PathVariable("time") Integer time) {
    busyService.runInAllProcessors(percentage, time);
  }

  /**
   * Cpu single processor.
   *
   * @param percentage the percentage
   * @param time the time
   */
  @Operation(
      summary = "Cpu single processors operation",
      description = "Cpu single processors operation",
      tags = {"disk"})
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successful operation",
            content =
                @Content(array = @ArraySchema(schema = @Schema(implementation = String.class)))),
        @ApiResponse(responseCode = "500", description = "Internal server error")
      })
  @RequestMapping(value = "/single/{percentage}/{time}", method = RequestMethod.GET)
  public void cpuSingleProcessor(
      @PathVariable("percentage") Integer percentage, @PathVariable("time") Integer time) {
    busyService.runInSingleProcessor(percentage, time);
  }
}
