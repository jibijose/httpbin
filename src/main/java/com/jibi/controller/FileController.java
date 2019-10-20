package com.jibi.controller;

import io.swagger.annotations.*;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Api(value = "File Api")
@RestController(value = "File Api")
@RequestMapping("/file")
public class FileController {
    protected static Map<String, List<String>> FILEGROUPTYPES = new HashMap<>();
    protected static Map<String, List<String>> FILETYPESIZES = new HashMap<>();

    private static List<String> IMAGETYPES = Arrays.asList("jpg", "gif", "png", "tiff", "ico");
    private static List<String> AUDIOYPES = Arrays.asList("mp3", "wav", "ogg");

    private static List<String> JPGSIZES = Arrays.asList("100KB", "500KB", "1MB", "2.5MB");
    private static List<String> GIFSIZES = Arrays.asList("500KB", "1MB", "3.5MB");
    private static List<String> PNGSIZES = Arrays.asList("500KB", "1MB", "2MB", "3MB");
    private static List<String> TIFFSIZES = Arrays.asList("1MB", "5MB", "10MB");
    private static List<String> ICOSIZES = Arrays.asList("400B");

    private static List<String> MP3SIZES = Arrays.asList("700KB", "1MB", "2MB", "5MB");
    private static List<String> WAVSIZES = Arrays.asList("1MB", "2MB", "5MB", "10MB");
    private static List<String> OGGSIZES = Arrays.asList("1MB", "2MB", "5MB");

    static {
        FILEGROUPTYPES.put("image", IMAGETYPES);
        FILEGROUPTYPES.put("audio", AUDIOYPES);

        FILETYPESIZES.put("jpg", JPGSIZES);
        FILETYPESIZES.put("gif", GIFSIZES);
        FILETYPESIZES.put("png", PNGSIZES);
        FILETYPESIZES.put("tiff", TIFFSIZES);
        FILETYPESIZES.put("ico", ICOSIZES);
        FILETYPESIZES.put("mp3", MP3SIZES);
        FILETYPESIZES.put("wav", WAVSIZES);
        FILETYPESIZES.put("ogg", OGGSIZES);
    }

    /************************************************************************************************************************************************/

    private byte[] getFileContentInternal(String fileGroup, String fileType, String size) throws IOException {
        InputStream in = getClass().getResourceAsStream("/file/" + fileGroup + "/" + fileType + "/" + size + "." + fileType);
        byte[] fileData = IOUtils.toByteArray(in);
        in.close();
        return fileData;
    }

    private byte[] getFileContent(String fileGroup, String fileType) throws IOException {

        if ("random".equals(fileType)) {
            int randInt = ThreadLocalRandom.current().nextInt(0, FILEGROUPTYPES.get(fileGroup).size());
            fileType = FILEGROUPTYPES.get(fileGroup).get(randInt);
        }

        String size = null;
        int randInt = ThreadLocalRandom.current().nextInt(0, FILETYPESIZES.get(fileType).size());
        size = FILETYPESIZES.get(fileType).get(randInt);

        return getFileContentInternal(fileGroup, fileType, size);
    }

    private byte[] getFileContent(String fileGroup, String fileTypeName, String size) throws IOException {
        if ("random".equals(size)) {
            int randInt = ThreadLocalRandom.current().nextInt(0, FILETYPESIZES.get(fileTypeName).size());
            size = FILETYPESIZES.get(fileTypeName).get(randInt);
        }
        return getFileContentInternal(fileGroup, fileTypeName, size);
    }

    /************************************************************************************************************************************************/

    @ApiOperation(value = "File image file operation", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/image/{fileType}", method = RequestMethod.GET, produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_PNG_VALUE, "image/tiff", "image/x-icon"})
    public @ResponseBody
    byte[] imageFileType(@ApiParam(value = "File type", allowableValues = "random, jpg, gif, png, tiff, ico") @PathVariable("fileType") String fileType) throws IOException {
        return getFileContent("image", fileType);
    }

    @ApiOperation(value = "File audio file operation", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/audio/{fileType}", method = RequestMethod.GET, produces = {"audio/mpeg3", "audio/wav", "audio/ogg"})
    public @ResponseBody
    byte[] audioFileType(@ApiParam(value = "File type", allowableValues = "random, mp3, wav, ogg") @PathVariable("fileType") String fileType) throws IOException {
        return getFileContent("audio", fileType);
    }

    /************************************************************************************************************************************************/

    @ApiOperation(value = "File image jpg file operation", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/image/jpg/{size}", method = RequestMethod.GET, produces = {MediaType.IMAGE_JPEG_VALUE})
    public @ResponseBody
    byte[] imageJpgSize(@ApiParam(value = "Jpg file size", allowableValues = "random, 100KB, 500KB, 1MB, 2.5MB") @PathVariable("size") String size) throws IOException {
        return getFileContent("image", "jpg", size);
    }

    @ApiOperation(value = "File image gif file operation", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/image/gif/{size}", method = RequestMethod.GET, produces = {MediaType.IMAGE_GIF_VALUE})
    public @ResponseBody
    byte[] imageGifSize(@ApiParam(value = "Gif file size", allowableValues = "500KB, 1MB, 3.5MB") @PathVariable("size") String size) throws IOException {
        return getFileContent("image", "gif", size);
    }

    @ApiOperation(value = "File image png file operation", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/image/png/{size}", method = RequestMethod.GET, produces = {MediaType.IMAGE_PNG_VALUE})
    public @ResponseBody
    byte[] imagePngSize(@ApiParam(value = "Png file size", allowableValues = "500KB, 1MB, 2MB, 3MB") @PathVariable("size") String size) throws IOException {
        return getFileContent("image", "png", size);
    }

    @ApiOperation(value = "File image tiff file operation", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/image/tiff/{size}", method = RequestMethod.GET, produces = {"image/tiff"})
    public @ResponseBody
    byte[] imageTiffSize(@ApiParam(value = "Tiff file size", allowableValues = "1MB, 5MB, 10MB") @PathVariable("size") String size) throws IOException {
        return getFileContent("image", "tiff", size);
    }

    @ApiOperation(value = "File image ico file operation", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/image/ico/{size}", method = RequestMethod.GET, produces = {"image/x-icon"})
    public @ResponseBody
    byte[] imageIcoSize(@ApiParam(value = "Ico file size", allowableValues = "400B") @PathVariable("size") String size) throws IOException {
        return getFileContent("image", "ico", size);
    }

    /************************************************************************************************************************************************/

    @ApiOperation(value = "File audio mp3 file operation", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/audio/mp3/{size}", method = RequestMethod.GET, produces = {"audio/mpeg3"})
    public @ResponseBody
    byte[] audioMp3Size(@ApiParam(value = "Mp3 file size", allowableValues = "700KB, 1MB, 2MB, 5MB") @PathVariable("size") String size) throws IOException {
        return getFileContent("audio", "mp3", size);
    }

    @ApiOperation(value = "File audio wav file operation", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/audio/wav/{size}", method = RequestMethod.GET, produces = {"audio/wav"})
    public @ResponseBody
    byte[] audioWavSize(@ApiParam(value = "Wav file size", allowableValues = "1MB, 2MB, 5MB, 10MB") @PathVariable("size") String size) throws IOException {
        return getFileContent("audio", "wav", size);
    }

    @ApiOperation(value = "File audio ogg file operation", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/audio/wav/{size}", method = RequestMethod.GET, produces = {"audio/ogg"})
    public @ResponseBody
    byte[] audioOggSize(@ApiParam(value = "Ogg file size", allowableValues = "1MB, 2MB, 5MB") @PathVariable("size") String size) throws IOException {
        return getFileContent("audio", "ogg", size);
    }

    /************************************************************************************************************************************************/


}