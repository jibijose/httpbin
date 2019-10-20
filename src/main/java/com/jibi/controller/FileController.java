package com.jibi.controller;

import io.swagger.annotations.*;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
        FILELOCATION.put("jpg", "/file/image/jpg/100KB.jpg");
        FILELOCATION.put("jpg100KB", "/file/image/jpg/100KB.jpg");
        FILELOCATION.put("jpg500KB", "/file/image/jpg/500KB.jpg");
        FILELOCATION.put("jpg1MB", "/file/image/jpg/1MB.jpg");
        FILELOCATION.put("jpg2.5MB", "/file/image/jpg/2.5MB.jpg");

        FILELOCATION.put("gif", "/file/image/gif/500KB.gif");
        FILELOCATION.put("gif500KB", "/file/image/gif/500KB.gif");
        FILELOCATION.put("gif1MB", "/file/image/gif/1MB.gif");
        FILELOCATION.put("gif3.5MB", "/file/image/gif/3.5MB.gif");

        FILELOCATION.put("png", "/file/image/png/500KB.png");
        FILELOCATION.put("png500KB", "/file/image/png/500KB.png");
        FILELOCATION.put("png1MB", "/file/image/png/1MB.png");
        FILELOCATION.put("png2MB", "/file/image/png/2MB.png");
        FILELOCATION.put("png3MB", "/file/image/png/3MB.png");

        FILELOCATION.put("tiff", "/file/image/tiff/1MB.tiff");
        FILELOCATION.put("tiff1MB", "/file/image/tiff/1MB.tiff");
        FILELOCATION.put("tiff5MB", "/file/image/tiff/5MB.tiff");
        FILELOCATION.put("tiff10MB", "/file/image/tiff/10MB.tiff");

        FILELOCATION.put("ico", "/file/image/ico/400B.ico");
        FILELOCATION.put("ico400B", "/file/image/ico/400B.ico");
    }

    private byte[] getFileContent(String fileType) throws IOException {
        InputStream in = getClass().getResourceAsStream(FILELOCATION.get(fileType));
        byte[] fileData = IOUtils.toByteArray(in);
        in.close();
        return fileData;
    }

    @ApiOperation(value = "File image file operation", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/image/{fileType}", method = RequestMethod.GET, produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_PNG_VALUE, "image/tiff", "image/x-icon"})
    public @ResponseBody
    byte[] imageFileType(@ApiParam(value = "File type", allowableValues = "jpg, gif, png, tiff, ico") @PathVariable("fileType") String fileType) throws IOException {
        return getFileContent(fileType);
    }

    @ApiOperation(value = "File image jpg file operation", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/image/jpg/{size}", method = RequestMethod.GET, produces = {MediaType.IMAGE_JPEG_VALUE})
    public @ResponseBody
    byte[] imageJpgSize(@ApiParam(value = "Jpg file size", allowableValues = "100KB, 500KB, 1MB, 2.5MB") @PathVariable("size") String size) throws IOException {
        return getFileContent("jpg" + size);
    }

    @ApiOperation(value = "File image gif file operation", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/image/gif/{size}", method = RequestMethod.GET, produces = {MediaType.IMAGE_GIF_VALUE})
    public @ResponseBody
    byte[] imageGifSize(@ApiParam(value = "Gif file size", allowableValues = "500KB, 1MB, 3.5MB") @PathVariable("size") String size) throws IOException {
        return getFileContent("gif" + size);
    }

    @ApiOperation(value = "File image png file operation", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/image/png/{size}", method = RequestMethod.GET, produces = {MediaType.IMAGE_PNG_VALUE})
    public @ResponseBody
    byte[] imagePngSize(@ApiParam(value = "Png file size", allowableValues = "500KB, 1MB, 2MB, 3MB") @PathVariable("size") String size) throws IOException {
        return getFileContent("png" + size);
    }

    @ApiOperation(value = "File image tiff file operation", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/image/tiff/{size}", method = RequestMethod.GET, produces = {"image/tiff"})
    public @ResponseBody
    byte[] imageTiffSize(@ApiParam(value = "Tiff file size", allowableValues = "1MB, 5MB, 10MB") @PathVariable("size") String size) throws IOException {
        return getFileContent("tiff" + size);
    }

    @ApiOperation(value = "File image ico file operation", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/image/ico/{size}", method = RequestMethod.GET, produces = {"image/x-icon"})
    public @ResponseBody
    byte[] imageIcoSize(@ApiParam(value = "Ico file size", allowableValues = "400B") @PathVariable("size") String size) throws IOException {
        return getFileContent("ico" + size);
    }

}