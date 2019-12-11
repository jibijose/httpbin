package com.jibi.controller;

import com.jibi.service.BusyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Cpu Api")
@RestController(value = "Cpu Api")
@RequestMapping("/cpu")
public class CpuController {

  @Autowired private BusyService busyService;

  @ApiOperation(value = "Cpu all processors operation", response = String.class)
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Ok"),
        @ApiResponse(code = 500, message = "Internal server error")
      })
  @RequestMapping(value = "/all/{percentage}/{time}", method = RequestMethod.GET)
  public void cpuAllProcessors(
      @PathVariable("percentage") Integer percentage, @PathVariable("time") Integer time) {
    busyService.runInAllProcessors(percentage, time);
  }

  @ApiOperation(value = "Cpu single processors operation", response = String.class)
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Ok"),
        @ApiResponse(code = 500, message = "Internal server error")
      })
  @RequestMapping(value = "/single/{percentage}/{time}", method = RequestMethod.GET)
  public void cpuSingleProcessor(
      @PathVariable("percentage") Integer percentage, @PathVariable("time") Integer time) {
    busyService.runInSingleProcessor(percentage, time);
  }
}
