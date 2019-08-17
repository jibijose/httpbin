package com.jibi.controller;

import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@Api(value = "Status codes Api")
public class StatusCodesController {

    @ApiOperation(value = "Status code api", notes = "Api to respond with the status code in uri", response = Void.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/status/{code}", method = RequestMethod.GET)
    public ResponseEntity<Void> statusCode(@ApiParam(value = "Status code of response", required = true) @PathVariable("code") Integer code) {
        HttpStatus httpStatus = HttpStatus.valueOf(code);
        return new ResponseEntity<Void>(httpStatus);
    }

    @ApiOperation(value = "Status random code api", response = Void.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/status/random/{codes}", method = RequestMethod.GET)
    public ResponseEntity<Void> statusRandomCodes(@ApiParam(value = "Comma separated status codes", required = true) @PathVariable("codes") String strCodes) {
        String[] arrayCodes = strCodes.split(",");
        List<Integer> codes = Arrays.stream(arrayCodes).map(str -> Integer.parseInt(str)).collect(Collectors.toList());
        Random random = new Random(new Date().getTime());
        Integer randomRangeIndex = random.ints(0, arrayCodes.length).limit(1).findFirst().getAsInt();
        HttpStatus httpStatus = HttpStatus.valueOf(codes.get(randomRangeIndex));
        return new ResponseEntity<Void>(httpStatus);
    }
}
