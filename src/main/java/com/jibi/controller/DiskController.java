package com.jibi.controller;

import com.jibi.common.Util;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Disk Api", description = "Disk Api")
@RestController(value = "Disk Api")
@RequestMapping("/disk")
@Slf4j
public class DiskController {

    private static volatile byte[] BYTES1MB = null;

    @Operation(
            summary = "Disk write api",
            description = "Disk write api",
            tags = {"disk"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content =
                            @Content(array = @ArraySchema(schema = @Schema(implementation = Void.class)))),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    @Parameter(
            name = "unit",
            schema =
            @Schema(
                    description = "Umit",
                    type = "string",
                    allowableValues = {"MB", "GB"}))
    @Parameter(name = "count", schema = @Schema(description = "Count", type = "integer"))
    @RequestMapping(
            value = "/write/{unit}/{count}",
            method = RequestMethod.GET,
            produces = {MediaType.TEXT_PLAIN_VALUE})
    public void write(@PathVariable("unit") String unit, @PathVariable("count") Integer count)
            throws IOException {
        if (!"GB".equals(unit) && !"MB".equals(unit)) {
            throw new RuntimeException("Unit must be MB or GB");
        }
        if ("GB".equals(unit)) {
            count = count * 1024;
        }
        byte[] bytes = getCachedBytes1MB();
        IntStream.range(0, count)
                .forEach(
                        index -> {
                            try {
                                writeAndDeleteTempFile(bytes);
                            } catch (IOException ioException) {
                                throw new RuntimeException("Write failed", ioException);
                            }
                        });
    }

    @Operation(
            summary = "Disk read api",
            description = "Disk read api",
            tags = {"disk"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content =
                            @Content(array = @ArraySchema(schema = @Schema(implementation = Void.class)))),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    @Parameter(
            name = "unit",
            schema =
            @Schema(
                    description = "Umit",
                    type = "string",
                    allowableValues = {"MB", "GB"}))
    @Parameter(name = "count", schema = @Schema(description = "Count", type = "integer"))
    @RequestMapping(
            value = "/read/{unit}/{count}",
            method = RequestMethod.GET,
            produces = {MediaType.TEXT_PLAIN_VALUE})
    public void read(@PathVariable("unit") String unit, @PathVariable("count") Integer count) {
        if (!"GB".equals(unit) && !"MB".equals(unit)) {
            throw new RuntimeException("Unit must be MB or GB");
        }
        if ("GB".equals(unit)) {
            count = count * 1024;
        }

        IntStream.range(0, count)
                .forEach(
                        index -> {
                            try {
                                byte[] bytes = getFileContent("1MB");
                            } catch (IOException ioException) {
                                throw new RuntimeException("Read failed", ioException);
                            }
                        });
    }

    private byte[] getCachedBytes1MB() throws IOException {
        if (BYTES1MB == null) {
            synchronized (this) {
                BYTES1MB = getFileContent("1MB");
            }
        }
        return BYTES1MB;
    }

    private byte[] getFileContent(String size) throws IOException {
        try (InputStream in = getClass().getResourceAsStream("/file/other/txt/" + size + ".txt")) {
            byte[] fileData = IOUtils.toByteArray(in);
            return fileData;
        }
    }

    private void writeAndDeleteTempFile(byte[] bytes) throws IOException {
        File file = null;
        try (InputStream inputStream = getClass().getResourceAsStream("/file/other/txt/1MB.txt")) {
            file = new File("temp" + Util.randomNumber(0, Integer.MAX_VALUE) + ".txt");
            FileUtils.writeByteArrayToFile(file, bytes);
        } finally {
            if (file != null) {
                boolean deleteStatus = file.delete();
                log.trace("File {} delete status is {}", file, deleteStatus);
            }
        }
    }
}
