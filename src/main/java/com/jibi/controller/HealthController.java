package com.jibi.controller;

import com.jibi.model.HealthModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Health Api", description = "Health Api")
@RestController(value = "Health Api")
@RequestMapping("/health")
public class HealthController {
  @Operation(
      summary = "Health api",
      description = "Health api",
      tags = {"health"})
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successful operation",
            content =
                @Content(
                    array = @ArraySchema(schema = @Schema(implementation = HealthModel.class)))),
        @ApiResponse(responseCode = "500", description = "Internal server error")
      })
  @RequestMapping(
      value = "",
      method = RequestMethod.GET,
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public HealthModel health() {
    return new HealthModel();
  }
}
