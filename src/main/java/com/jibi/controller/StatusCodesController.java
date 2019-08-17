package com.jibi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Api(value = "Status codes endpoints")
public class StatusCodesController {

    @ApiOperation(value = "Status code api", response = Void.class)
    @RequestMapping(value = "/status/{code}", method = RequestMethod.GET)
    public ResponseEntity<Void> statusCode() {
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
