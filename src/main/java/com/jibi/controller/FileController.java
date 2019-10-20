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

    private static Map<String, String> FILELOCATION = new HashMap<>();

    protected static List<String> IMAGETYPES = Arrays.asList("jpg", "gif", "png", "tiff", "ico");
    protected static List<String> AUDIOYPES = Arrays.asList("mp3", "wav", "ogg");

    protected static List<String> JPGSIZES = Arrays.asList("100KB", "500KB", "1MB", "2.5MB");
    protected static List<String> GIFSIZES = Arrays.asList("500KB", "1MB", "3.5MB");
    protected static List<String> PNGSIZES = Arrays.asList("500KB", "1MB", "2MB", "3MB");
    protected static List<String> TIFFSIZES = Arrays.asList("1MB", "5MB", "10MB");
    protected static List<String> ICOSIZES = Arrays.asList("400B");

    protected static List<String> MP3SIZES = Arrays.asList("700KB", "1MB", "2MB", "5MB");
    protected static List<String> WAVSIZES = Arrays.asList("1MB", "2MB", "5MB", "10MB");
    protected static List<String> OGGSIZES = Arrays.asList("1MB", "2MB", "5MB");

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

        FILELOCATION.put("mp3", "/file/audio/mp3/700KB.mp3");
        FILELOCATION.put("wav", "/file/audio/wav/1MB.wav");
        FILELOCATION.put("ogg", "/file/audio/ogg/1MB.ogg");
    }

    /************************************************************************************************************************************************/

    private byte[] getFileContent(String fileType) throws IOException {
        InputStream in = getClass().getResourceAsStream(FILELOCATION.get(fileType));
        byte[] fileData = IOUtils.toByteArray(in);
        in.close();
        return fileData;
    }

    private byte[] getFileContent(List<String> fileTypeNames, String fileType) throws IOException {
        if ("random".equals(fileType)) {
            int randInt = ThreadLocalRandom.current().nextInt(0, fileTypeNames.size());
            return getFileContent(fileTypeNames.get(randInt));
        }
        return getFileContent(fileType);
    }

    private byte[] getFileContent(List<String> fileTypeNameSizes, String fileTypeName, String size) throws IOException {
        if ("random".equals(size)) {
            int randInt = ThreadLocalRandom.current().nextInt(0, fileTypeNameSizes.size());
            return getFileContent(fileTypeName + fileTypeNameSizes.get(randInt));
        }
        return getFileContent(fileTypeName + size);
    }

    /************************************************************************************************************************************************/

    @ApiOperation(value = "File image file operation", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/image/{fileType}", method = RequestMethod.GET, produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_PNG_VALUE, "image/tiff", "image/x-icon"})
    public @ResponseBody
    byte[] imageFileType(@ApiParam(value = "File type", allowableValues = "random, jpg, gif, png, tiff, ico") @PathVariable("fileType") String fileType) throws IOException {
        return getFileContent(IMAGETYPES, fileType);
    }

    @ApiOperation(value = "File audio file operation", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/audio/{fileType}", method = RequestMethod.GET, produces = {"audio/mpeg3", "audio/wav", "audio/ogg"})
    public @ResponseBody
    byte[] audioFileType(@ApiParam(value = "File type", allowableValues = "random, mp3, wav, ogg") @PathVariable("fileType") String fileType) throws IOException {
        return getFileContent(AUDIOYPES, fileType);
    }

    /************************************************************************************************************************************************/

    @ApiOperation(value = "File image jpg file operation", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/image/jpg/{size}", method = RequestMethod.GET, produces = {MediaType.IMAGE_JPEG_VALUE})
    public @ResponseBody
    byte[] imageJpgSize(@ApiParam(value = "Jpg file size", allowableValues = "random, 100KB, 500KB, 1MB, 2.5MB") @PathVariable("size") String size) throws IOException {
        return getFileContent(JPGSIZES, "jpg", size);
    }

    @ApiOperation(value = "File image gif file operation", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/image/gif/{size}", method = RequestMethod.GET, produces = {MediaType.IMAGE_GIF_VALUE})
    public @ResponseBody
    byte[] imageGifSize(@ApiParam(value = "Gif file size", allowableValues = "500KB, 1MB, 3.5MB") @PathVariable("size") String size) throws IOException {
        return getFileContent(GIFSIZES, "gif", size);
    }

    @ApiOperation(value = "File image png file operation", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/image/png/{size}", method = RequestMethod.GET, produces = {MediaType.IMAGE_PNG_VALUE})
    public @ResponseBody
    byte[] imagePngSize(@ApiParam(value = "Png file size", allowableValues = "500KB, 1MB, 2MB, 3MB") @PathVariable("size") String size) throws IOException {
        return getFileContent(PNGSIZES, "png", size);
    }

    @ApiOperation(value = "File image tiff file operation", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/image/tiff/{size}", method = RequestMethod.GET, produces = {"image/tiff"})
    public @ResponseBody
    byte[] imageTiffSize(@ApiParam(value = "Tiff file size", allowableValues = "1MB, 5MB, 10MB") @PathVariable("size") String size) throws IOException {
        return getFileContent(TIFFSIZES, "tiff", size);
    }

    @ApiOperation(value = "File image ico file operation", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/image/ico/{size}", method = RequestMethod.GET, produces = {"image/x-icon"})
    public @ResponseBody
    byte[] imageIcoSize(@ApiParam(value = "Ico file size", allowableValues = "400B") @PathVariable("size") String size) throws IOException {
        return getFileContent(ICOSIZES, "ico", size);
    }

    /************************************************************************************************************************************************/

    @ApiOperation(value = "File audio mp3 file operation", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/audio/mp3/{size}", method = RequestMethod.GET, produces = {"audio/mpeg3"})
    public @ResponseBody
    byte[] audioMp3Size(@ApiParam(value = "Mp3 file size", allowableValues = "700KB, 1MB, 2MB, 5MB") @PathVariable("size") String size) throws IOException {
        return getFileContent(MP3SIZES, "mp3", size);
    }

    @ApiOperation(value = "File audio wav file operation", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/audio/wav/{size}", method = RequestMethod.GET, produces = {"audio/wav"})
    public @ResponseBody
    byte[] audioWavSize(@ApiParam(value = "Wav file size", allowableValues = "1MB, 2MB, 5MB, 10MB") @PathVariable("size") String size) throws IOException {
        return getFileContent(WAVSIZES, "wav", size);
    }

    @ApiOperation(value = "File audio ogg file operation", response = byte[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(value = "/audio/wav/{size}", method = RequestMethod.GET, produces = {"audio/ogg"})
    public @ResponseBody
    byte[] audioOggSize(@ApiParam(value = "Ogg file size", allowableValues = "1MB, 2MB, 5MB") @PathVariable("size") String size) throws IOException {
        return getFileContent(OGGSIZES, "ogg", size);
    }

    /************************************************************************************************************************************************/



}