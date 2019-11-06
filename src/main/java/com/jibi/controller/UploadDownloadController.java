package com.jibi.controller;

import com.jibi.model.UploadInfoModel;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Api(value = "Upload Download Api")
@RestController(value = "Upload Download Api")
public class UploadDownloadController {

    @Autowired
    private FileController fileController;

    @ApiOperation(value = "Upload api", response = UploadInfoModel.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public UploadInfoModel upload(@RequestParam("file") MultipartFile file) throws IOException {
        UploadInfoModel uploadInfoModel = new UploadInfoModel();
        byte[] bytes = file.getBytes();
        uploadInfoModel.setUploadedBytesLength(Integer.toString(bytes.length));
        uploadInfoModel.setContentType(file.getContentType());
        uploadInfoModel.setName(file.getName());
        uploadInfoModel.setOriginalFileName(file.getOriginalFilename());
        uploadInfoModel.setSize(Long.toString(file.getSize()));
        if (file.getSize() == bytes.length) {
            uploadInfoModel.setStatus("success");
        }

        return uploadInfoModel;
    }

    @ApiOperation(value = "Download api", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/download", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public byte[] download() throws IOException {
        return fileController.otherFileType("txt");
    }

    @ApiOperation(value = "Download Size api", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/download/{size}", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public byte[] downloadSize(@ApiParam(value = "Txt file size", allowableValues = "1B, 10B, 100B, 1KB, 10KB, 100KB, 1MB, 10MB") @PathVariable("size") String size) throws IOException {
        return fileController.otherTxtSize(size);
    }

}