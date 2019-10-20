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
        FILELOCATION.put("jpg100KB", "/file/image/jpg/100KB.jpg");
        FILELOCATION.put("jpg500KB", "/file/image/jpg/500KB.jpg");
        FILELOCATION.put("jpg1MB", "/file/image/jpg/1MB.jpg");
        FILELOCATION.put("jpg2.5MB", "/file/image/jpg/2.5MB.jpg");

        FILELOCATION.put("gif500MB", "/file/image/gif/500KB.gif");
        FILELOCATION.put("gif1MB", "/file/image/gif/1MB.gif");
        FILELOCATION.put("gif3.5MB", "/file/image/gif/3.5MB.gif");

        FILELOCATION.put("png500KB", "/file/image/png/500KB.png");
        FILELOCATION.put("png1MB", "/file/image/png/1MB.png");
        FILELOCATION.put("png2MB", "/file/image/png/2MB.png");
        FILELOCATION.put("png3MB", "/file/image/png/3MB.png");

        FILELOCATION.put("tiff1MB", "/file/image/tiff/1MB.tiff");
        FILELOCATION.put("tiff5MB", "/file/image/tiff/5MB.tiff");
        FILELOCATION.put("tiff10MB", "/file/image/tiff/10MB.tiff");

        FILELOCATION.put("ico400B", "/file/image/ico/400B.ico");
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
        return getFileContent("jpg100KB");
    }

    @ApiOperation(value = "File 100KB jpg operation", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/100KB/jpg", method = RequestMethod.GET, produces = {MediaType.IMAGE_JPEG_VALUE})
    public @ResponseBody
    byte[] jpg100KB() throws IOException {
        return getFileContent("jpg100KB");
    }

    @ApiOperation(value = "File 500KB jpg operation", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/500KB/jpg", method = RequestMethod.GET, produces = {MediaType.IMAGE_JPEG_VALUE})
    public @ResponseBody
    byte[] jpg500KB() throws IOException {
        return getFileContent("jpg500KB");
    }

    @ApiOperation(value = "File 1MB jpg operation", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/1MB/jpg", method = RequestMethod.GET, produces = {MediaType.IMAGE_JPEG_VALUE})
    public @ResponseBody
    byte[] jpg1MB() throws IOException {
        return getFileContent("jpg1MB");
    }

    @ApiOperation(value = "File 2.5MB jpg operation", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/2.5MB/jpg", method = RequestMethod.GET, produces = {MediaType.IMAGE_JPEG_VALUE})
    public @ResponseBody
    byte[] jpg2p5MB() throws IOException {
        return getFileContent("jpg2.5MB");
    }

    @ApiOperation(value = "File gif operation", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/gif", method = RequestMethod.GET, produces = {MediaType.IMAGE_GIF_VALUE})
    public @ResponseBody
    byte[] gif() throws IOException {
        return getFileContent("gif500KB");
    }

    @ApiOperation(value = "File png operation", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/png", method = RequestMethod.GET, produces = {MediaType.IMAGE_PNG_VALUE})
    public @ResponseBody
    byte[] png() throws IOException {
        return getFileContent("png500KB");
    }

    @ApiOperation(value = "File tiff operation", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/tiff", method = RequestMethod.GET, produces = {"image/tiff"})
    public @ResponseBody
    byte[] tiff() throws IOException {
        return getFileContent("tiff1MB");
    }

    @ApiOperation(value = "File ico operation", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/ico", method = RequestMethod.GET, produces = {"image/vnd.microsoft.icon"})
    public @ResponseBody
    byte[] ico() throws IOException {
        return getFileContent("ico400B");
    }

}