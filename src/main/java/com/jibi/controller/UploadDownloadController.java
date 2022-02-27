package com.jibi.controller;

import com.jibi.model.UploadInfoModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Upload Download Api", description = "Upload Download Api")
@RestController(value = "Upload Download Api")
public class UploadDownloadController {

    @Autowired
    private FileController fileController;

    @Operation(
            summary = "Upload api",
            description = "Upload api",
            tags = {"uploaddownload"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content =
                            @Content(
                                    array =
                                    @ArraySchema(schema = @Schema(implementation = UploadInfoModel.class)))),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    @RequestMapping(
            value = "/upload",
            method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
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

    @Operation(
            summary = "Download api",
            description = "Download api",
            tags = {"uploaddownload"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content =
                            @Content(array = @ArraySchema(schema = @Schema(implementation = byte[].class)))),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    @RequestMapping(
            value = "/download",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public byte[] download() throws IOException {
        return fileController.otherFileType("txt");
    }

    @Operation(
            summary = "Download size api",
            description = "Download size api",
            tags = {"uploaddownload"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content =
                            @Content(array = @ArraySchema(schema = @Schema(implementation = byte[].class)))),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    @Parameter(
            name = "size",
            schema =
            @Schema(
                    description = "Size",
                    type = "string",
                    allowableValues = {"1B", "10B", "100B", "1KB", "10KB", "100KB", "1MB", "10MB"}))
    @RequestMapping(
            value = "/download/{size}",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public byte[] downloadSize(@PathVariable("size") String size) throws IOException {
        return fileController.otherTxtSize(size);
    }
}
