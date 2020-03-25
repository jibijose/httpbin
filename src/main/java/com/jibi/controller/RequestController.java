package com.jibi.controller;

import io.swagger.annotations.*;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Request Api")
@RestController(value = "Request Api")
@RequestMapping("/request")
public class RequestController {

  @ApiOperation(value = "Request headers operation", response = Map.class)
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Ok"),
        @ApiResponse(code = 500, message = "Internal server error")
      })
  @RequestMapping(
      value = "/headers",
      method = RequestMethod.GET,
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public Map<String, String> headers(
      @ApiParam(value = "Map of headers") @RequestHeader Map<String, String> headers) {
    return headers;
  }
}
