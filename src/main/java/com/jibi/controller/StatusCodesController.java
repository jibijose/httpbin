package com.jibi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Status codes Api")
public class StatusCodesController {

    @ApiOperation(value = "Status code api", response = Void.class)
    @RequestMapping(value = "/status/{code}", method = RequestMethod.GET)
    public ResponseEntity<Void> statusCode(@PathVariable("code") Integer code) {
        HttpStatus httpStatus = HttpStatus.valueOf(code);
        return new ResponseEntity<Void>(httpStatus);
    }
}
