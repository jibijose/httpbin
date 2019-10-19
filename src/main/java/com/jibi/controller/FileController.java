package com.jibi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Api(value = "File Api")
@RestController(value = "File Api")
@RequestMapping("/file")
public class FileController {

    private static Map<String, String> FILELOCATION = new HashMap<>();

    static {
        FILELOCATION.put("jpg", "/file/jpg.jpg");
        FILELOCATION.put("gif", "/file/gif.gif");
        FILELOCATION.put("png", "/file/png.png");
        FILELOCATION.put("tiff", "/file/tiff.tiff");
        FILELOCATION.put("ico", "/file/ico.ico");
    }

    private byte[] getFileContent(String fileType) throws IOException {
        InputStream in = getClass().getResourceAsStream(FILELOCATION.get(fileType));
        byte[] fileData = IOUtils.toByteArray(in);
        in.close();
        return fileData;
    }

    @ApiOperation(value = "File jpg operation", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/jpg", method = RequestMethod.GET, produces = {MediaType.IMAGE_JPEG_VALUE})
    public @ResponseBody
    byte[] jpg() throws IOException {
        return getFileContent("jpg");
    }

    @ApiOperation(value = "File gif operation", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/gif", method = RequestMethod.GET, produces = {MediaType.IMAGE_GIF_VALUE})
    public @ResponseBody
    byte[] gif() throws IOException {
        return getFileContent("gif");
    }

    @ApiOperation(value = "File png operation", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/png", method = RequestMethod.GET, produces = {MediaType.IMAGE_PNG_VALUE})
    public @ResponseBody
    byte[] png() throws IOException {
        return getFileContent("png");
    }

    @ApiOperation(value = "File tiff operation", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/tiff", method = RequestMethod.GET, produces = {"image/tiff"})
    public @ResponseBody
    byte[] tiff() throws IOException {
        return getFileContent("tiff");
    }

    @ApiOperation(value = "File ico operation", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/ico", method = RequestMethod.GET, produces = {"image/vnd.microsoft.icon"})
    public @ResponseBody
    byte[] ico() throws IOException {
        return getFileContent("ico");
    }

}