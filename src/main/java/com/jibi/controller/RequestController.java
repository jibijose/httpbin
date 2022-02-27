package com.jibi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Request Api", description = "Request Api")
@RestController(value = "Request Api")
@RequestMapping("/request")
public class RequestController {
    @Operation(
            summary = "Request api",
            description = "Request headers operation",
            tags = {"request"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Map.class)))),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    @RequestMapping(
            value = "/headers",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, String> headers(@RequestHeader Map<String, String> headers) {
        return headers;
    }
}
