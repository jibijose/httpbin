package com.jibi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Status codes controller.
 */
@Tag(name = "Status code Api", description = "Status code Api")
@RestController(value = "Status codes Api")
@RequestMapping("/status")
public class StatusCodesController {

    /**
     * The Random.
     */
    Random random = new Random(new Date().getTime());

    /**
     * Status code response entity.
     *
     * @param code the code
     * @return the response entity
     */
    @Operation(
      summary = "Status code api",
      description = "Api to respond with the status code in uri",
      tags = {"status"})
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successful operation",
            content =
                @Content(array = @ArraySchema(schema = @Schema(implementation = Void.class)))),
        @ApiResponse(responseCode = "500", description = "Internal server error")
      })
  @Parameter(name = "code", schema = @Schema(description = "Code", type = "integer"))
  @RequestMapping(value = "/{code}", method = RequestMethod.GET)
  public ResponseEntity<Void> statusCode(@PathVariable Integer code) {
    HttpStatus httpStatus = HttpStatus.valueOf(code);
    return new ResponseEntity<Void>(httpStatus);
  }

    /**
     * Status random codes response entity.
     *
     * @param strCodes the str codes
     * @return the response entity
     */
    @Operation(
      summary = "Status code api",
      description = "Status random code api",
      tags = {"status"})
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successful operation",
            content =
                @Content(array = @ArraySchema(schema = @Schema(implementation = Void.class)))),
        @ApiResponse(responseCode = "500", description = "Internal server error")
      })
  @Parameter(name = "codes", schema = @Schema(description = "Codes", type = "integer"))
  @RequestMapping(value = "/random/{codes}", method = RequestMethod.GET)
  public ResponseEntity<Void> statusRandomCodes(@PathVariable("codes") String strCodes) {
    String[] arrayCodes = strCodes.split(",");
    List<Integer> codes =
        Arrays.stream(arrayCodes).map(str -> Integer.parseInt(str)).collect(Collectors.toList());
    Integer randomRangeIndex = random.ints(0, arrayCodes.length).limit(1).findFirst().getAsInt();
    HttpStatus httpStatus = HttpStatus.valueOf(codes.get(randomRangeIndex));
    return new ResponseEntity<Void>(httpStatus);
  }
}
