package com.jibi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Api(value = "Upload Download Api")
@RestController(value = "Upload Download Api")
public class UploadDownloadController {

    @ApiOperation(value = "Upload api", response = String.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        Map<String, String> uploadStatus = new HashMap<>();
        byte[] bytes = file.getBytes();
        uploadStatus.put("uploadedBytesLength", Integer.toString(bytes.length));
        uploadStatus.put("contentType", file.getContentType());
        uploadStatus.put("name", file.getName());
        uploadStatus.put("originalFileName", file.getOriginalFilename());
        uploadStatus.put("size", Long.toString(file.getSize()));
        if (file.getSize() == bytes.length) {
            uploadStatus.put("status", "success");
        }

        return uploadStatus;
    }

}