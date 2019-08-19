package com.jibi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Api(value = "Request Api")
@RestController(value = "Request Api")
@RequestMapping("/request")
public class RequestController {

    @ApiOperation(value = "Request headers operation", response = Map.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/headers", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Map<String, String> headers(@RequestHeader Map<String, String> headers) {
        return headers;
    }
}