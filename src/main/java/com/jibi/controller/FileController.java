package com.jibi.controller;

import io.swagger.annotations.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(value = "File Api")
@RestController(value = "File Api")
@RequestMapping("/file")
public class FileController {
  static final Map<String, List<String>> FILEGROUPTYPES = new HashMap<>();
  static final Map<String, List<String>> FILETYPESIZES = new HashMap<>();

  private static List<String> IMAGETYPES = Arrays.asList("jpg", "gif", "png", "tiff", "ico");
  private static List<String> VIDEOTYPES = Arrays.asList("avi", "mov", "mp4", "ogg", "wmv");
  private static List<String> AUDIOYPES = Arrays.asList("mp3", "wav", "ogg");
  private static List<String> DOCUMENTYPES =
      Arrays.asList("doc", "docx", "xls", "xlsx", "ppt", "pdf", "odp", "ods", "odt", "rtf");
  private static List<String> OTHERTYPES = Arrays.asList("csv", "html", "txt", "zip");

  private static List<String> IMAGEJPGSIZES = Arrays.asList("100KB", "500KB", "1MB", "2.5MB");
  private static List<String> IMAGEGIFSIZES = Arrays.asList("500KB", "1MB", "3.5MB");
  private static List<String> IMAGEPNGSIZES = Arrays.asList("500KB", "1MB", "2MB", "3MB");
  private static List<String> IMAGETIFFSIZES = Arrays.asList("1MB", "5MB", "10MB");
  private static List<String> IMAGEICOSIZES = Arrays.asList("400B");

  private static List<String> VIDEOAVISIZES = Arrays.asList("750KB", "800KB", "1.5MB", "2.3MB");
  private static List<String> VIDEOMOVSIZES = Arrays.asList("700KB", "800KB", "1.4MB", "2.2MB");
  private static List<String> VIDEOMP4SIZES = Arrays.asList("1.5MB", "3MB", "10MB", "18MB");
  private static List<String> VIDEOOGGSIZES = Arrays.asList("1.7MB", "2.7MB", "11.4MB", "13.3MB");
  private static List<String> VIDEOWMVSIZES = Arrays.asList("1.2MB", "1.6MB", "4.9MB", "9.3MB");

  private static List<String> AUDIOMP3SIZES = Arrays.asList("700KB", "1MB", "2MB", "5MB");
  private static List<String> AUDIOWAVSIZES = Arrays.asList("1MB", "2MB", "5MB", "10MB");
  private static List<String> AUDIOOGGSIZES = Arrays.asList("1MB", "2MB", "5MB");

  private static List<String> DOCUMENTDOCSIZES = Arrays.asList("100KB", "500KB", "1MB");
  private static List<String> DOCUMENTDOCXSIZES = Arrays.asList("100KB", "500KB", "1MB");
  private static List<String> DOCUMENTXLSSIZES =
      Arrays.asList("10KB", "14KB", "20KB", "140KB", "670KB");
  private static List<String> DOCUMENTXLSXSIZES =
      Arrays.asList("5KB", "7KB", "9KB", "42KB", "184KB");
  private static List<String> DOCUMENTPPTSIZES = Arrays.asList("250KB", "500KB", "1MB");
  private static List<String> DOCUMENTPDFSIZES = Arrays.asList("150KB", "500KB", "1MB");
  private static List<String> DOCUMENTODPSIZES = Arrays.asList("200KB", "500KB", "1MB");
  private static List<String> DOCUMENTODSSIZES = Arrays.asList("31KB", "67KB", "94KB", "231KB");
  private static List<String> DOCUMENTODTSIZES = Arrays.asList("100KB", "500KB", "1MB");
  private static List<String> DOCUMENTRTFSIZES = Arrays.asList("100KB", "300KB", "500KB", "1MB");

  private static List<String> OTHERCSVSIZES = Arrays.asList("7KB");
  private static List<String> OTHERHTMLSIZES = Arrays.asList("4KB");
  private static List<String> OTHERTXTSIZES =
      Arrays.asList("1B", "10B", "100B", "1KB", "10KB", "100KB", "1MB", "10MB");
  private static List<String> OTHERZIPSIZES = Arrays.asList("2MB", "5MB", "9MB", "10MB");

  static {
    FILEGROUPTYPES.put("image", IMAGETYPES);
    FILEGROUPTYPES.put("video", VIDEOTYPES);
    FILEGROUPTYPES.put("audio", AUDIOYPES);
    FILEGROUPTYPES.put("document", DOCUMENTYPES);
    FILEGROUPTYPES.put("other", OTHERTYPES);

    FILETYPESIZES.put("image_jpg", IMAGEJPGSIZES);
    FILETYPESIZES.put("image_gif", IMAGEGIFSIZES);
    FILETYPESIZES.put("image_png", IMAGEPNGSIZES);
    FILETYPESIZES.put("image_tiff", IMAGETIFFSIZES);
    FILETYPESIZES.put("image_ico", IMAGEICOSIZES);

    FILETYPESIZES.put("video_avi", VIDEOAVISIZES);
    FILETYPESIZES.put("video_mov", VIDEOMOVSIZES);
    FILETYPESIZES.put("video_mp4", VIDEOMP4SIZES);
    FILETYPESIZES.put("video_ogg", VIDEOOGGSIZES);
    FILETYPESIZES.put("video_wmv", VIDEOWMVSIZES);

    FILETYPESIZES.put("audio_mp3", AUDIOMP3SIZES);
    FILETYPESIZES.put("audio_wav", AUDIOWAVSIZES);
    FILETYPESIZES.put("audio_ogg", AUDIOOGGSIZES);

    FILETYPESIZES.put("document_doc", DOCUMENTDOCSIZES);
    FILETYPESIZES.put("document_docx", DOCUMENTDOCXSIZES);
    FILETYPESIZES.put("document_xls", DOCUMENTXLSSIZES);
    FILETYPESIZES.put("document_xlsx", DOCUMENTXLSXSIZES);
    FILETYPESIZES.put("document_ppt", DOCUMENTPPTSIZES);
    FILETYPESIZES.put("document_pdf", DOCUMENTPDFSIZES);
    FILETYPESIZES.put("document_odp", DOCUMENTODPSIZES);
    FILETYPESIZES.put("document_ods", DOCUMENTODSSIZES);
    FILETYPESIZES.put("document_odt", DOCUMENTODTSIZES);
    FILETYPESIZES.put("document_rtf", DOCUMENTRTFSIZES);

    FILETYPESIZES.put("other_csv", OTHERCSVSIZES);
    FILETYPESIZES.put("other_html", OTHERHTMLSIZES);
    FILETYPESIZES.put("other_txt", OTHERTXTSIZES);
    FILETYPESIZES.put("other_zip", OTHERZIPSIZES);
  }

  /**
   * *********************************************************************************************************************************************
   */
  private byte[] getFileContentInternal(String fileGroup, String fileType, String size)
      throws IOException {
    try (InputStream in =
        getClass()
            .getResourceAsStream(
                "/file/" + fileGroup + "/" + fileType + "/" + size + "." + fileType)) {
      byte[] fileData = IOUtils.toByteArray(in);
      return fileData;
    }
  }

  private byte[] getFileContent(String fileGroup, String fileType) throws IOException {

    if ("random".equals(fileType)) {
      int randInt = ThreadLocalRandom.current().nextInt(0, FILEGROUPTYPES.get(fileGroup).size());
      fileType = FILEGROUPTYPES.get(fileGroup).get(randInt);
    }

    String size = null;
    int randInt =
        ThreadLocalRandom.current()
            .nextInt(0, FILETYPESIZES.get(fileGroup + "_" + fileType).size());
    size = FILETYPESIZES.get(fileGroup + "_" + fileType).get(randInt);

    return getFileContentInternal(fileGroup, fileType, size);
  }

  private byte[] getFileContent(String fileGroup, String fileTypeName, String size)
      throws IOException {
    if ("random".equals(size)) {
      int randInt =
          ThreadLocalRandom.current()
              .nextInt(0, FILETYPESIZES.get(fileGroup + "_" + fileTypeName).size());
      size = FILETYPESIZES.get(fileGroup + "_" + fileTypeName).get(randInt);
    }
    return getFileContentInternal(fileGroup, fileTypeName, size);
  }

  /**
   * *********************************************************************************************************************************************
   */
  @ApiOperation(value = "File image file operation", response = byte[].class)
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")
    }
  )
  @RequestMapping(
    value = "/image/{fileType}",
    method = RequestMethod.GET,
    produces = {
      MediaType.IMAGE_JPEG_VALUE,
      MediaType.IMAGE_GIF_VALUE,
      MediaType.IMAGE_PNG_VALUE,
      "image/tiff",
      "image/x-icon"
    }
  )
  public @ResponseBody byte[] imageFileType(
      @ApiParam(value = "File type", allowableValues = "random, jpg, gif, png, tiff, ico")
          @PathVariable("fileType")
          String fileType)
      throws IOException {
    return getFileContent("image", fileType);
  }

  @ApiOperation(value = "File video file operation", response = byte[].class)
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")
    }
  )
  @RequestMapping(
    value = "/video/{fileType}",
    method = RequestMethod.GET,
    produces = {"video/avi", "video/mov", "video/mp4", "video/ogg", "video/wmv"}
  )
  public @ResponseBody byte[] videoFileType(
      @ApiParam(value = "File type", allowableValues = "avi, mov, mp4, png, ogg, wmv")
          @PathVariable("fileType")
          String fileType)
      throws IOException {
    return getFileContent("video", fileType);
  }

  @ApiOperation(value = "File audio file operation", response = byte[].class)
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")
    }
  )
  @RequestMapping(
    value = "/audio/{fileType}",
    method = RequestMethod.GET,
    produces = {"audio/mpeg3", "audio/wav", "audio/ogg"}
  )
  public @ResponseBody byte[] audioFileType(
      @ApiParam(value = "File type", allowableValues = "random, mp3, wav, ogg")
          @PathVariable("fileType")
          String fileType)
      throws IOException {
    return getFileContent("audio", fileType);
  }

  @ApiOperation(value = "File document file operation", response = byte[].class)
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")
    }
  )
  @RequestMapping(
    value = "/document/{fileType}",
    method = RequestMethod.GET,
    produces = {
      "application/doc",
      "application/excel",
      "application/powerpoint",
      "application/pdf",
      "application/odt",
      "application/ods",
      "application/odp",
      "application/rtf"
    }
  )
  public @ResponseBody byte[] documentFileType(
      @ApiParam(
            value = "File type",
            allowableValues = "random, doc, docx, xls, xlsx, ppt, pdf, odt, ods, odp, rtf"
          )
          @PathVariable("fileType")
          String fileType)
      throws IOException {
    return getFileContent("document", fileType);
  }

  @ApiOperation(value = "File other file operation", response = byte[].class)
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")
    }
  )
  @RequestMapping(
    value = "/other/{fileType}",
    method = RequestMethod.GET,
    produces = {"text/csv", "text/html", "text/plain", "application/zip"}
  )
  public @ResponseBody byte[] otherFileType(
      @ApiParam(value = "File type", allowableValues = "csv, html, txt, zip")
          @PathVariable("fileType")
          String fileType)
      throws IOException {
    return getFileContent("other", fileType);
  }

  /**
   * *********************************************************************************************************************************************
   */
  @ApiOperation(value = "File image jpg file operation", response = byte[].class)
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")
    }
  )
  @RequestMapping(
    value = "/image/jpg/{size}",
    method = RequestMethod.GET,
    produces = {MediaType.IMAGE_JPEG_VALUE}
  )
  public @ResponseBody byte[] imageJpgSize(
      @ApiParam(value = "Jpg file size", allowableValues = "random, 100KB, 500KB, 1MB, 2.5MB")
          @PathVariable("size")
          String size)
      throws IOException {
    return getFileContent("image", "jpg", size);
  }

  @ApiOperation(value = "File image gif file operation", response = byte[].class)
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")
    }
  )
  @RequestMapping(
    value = "/image/gif/{size}",
    method = RequestMethod.GET,
    produces = {MediaType.IMAGE_GIF_VALUE}
  )
  public @ResponseBody byte[] imageGifSize(
      @ApiParam(value = "Gif file size", allowableValues = "500KB, 1MB, 3.5MB")
          @PathVariable("size")
          String size)
      throws IOException {
    return getFileContent("image", "gif", size);
  }

  @ApiOperation(value = "File image png file operation", response = byte[].class)
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")
    }
  )
  @RequestMapping(
    value = "/image/png/{size}",
    method = RequestMethod.GET,
    produces = {MediaType.IMAGE_PNG_VALUE}
  )
  public @ResponseBody byte[] imagePngSize(
      @ApiParam(value = "Png file size", allowableValues = "500KB, 1MB, 2MB, 3MB")
          @PathVariable("size")
          String size)
      throws IOException {
    return getFileContent("image", "png", size);
  }

  @ApiOperation(value = "File image tiff file operation", response = byte[].class)
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")
    }
  )
  @RequestMapping(
    value = "/image/tiff/{size}",
    method = RequestMethod.GET,
    produces = {"image/tiff"}
  )
  public @ResponseBody byte[] imageTiffSize(
      @ApiParam(value = "Tiff file size", allowableValues = "1MB, 5MB, 10MB") @PathVariable("size")
          String size)
      throws IOException {
    return getFileContent("image", "tiff", size);
  }

  @ApiOperation(value = "File image ico file operation", response = byte[].class)
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")
    }
  )
  @RequestMapping(
    value = "/image/ico/{size}",
    method = RequestMethod.GET,
    produces = {"image/x-icon"}
  )
  public @ResponseBody byte[] imageIcoSize(
      @ApiParam(value = "Ico file size", allowableValues = "400B") @PathVariable("size")
          String size)
      throws IOException {
    return getFileContent("image", "ico", size);
  }

  /**
   * *********************************************************************************************************************************************
   */
  @ApiOperation(value = "File video avi file operation", response = byte[].class)
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")
    }
  )
  @RequestMapping(
    value = "/video/avi/{size}",
    method = RequestMethod.GET,
    produces = {"video/avi"}
  )
  public @ResponseBody byte[] videoAviSize(
      @ApiParam(value = "Avi file size", allowableValues = "random, 750KB, 800KB, 1.5MB, 2.3MB")
          @PathVariable("size")
          String size)
      throws IOException {
    return getFileContent("video", "avi", size);
  }

  @ApiOperation(value = "File video mov file operation", response = byte[].class)
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")
    }
  )
  @RequestMapping(
    value = "/video/mov/{size}",
    method = RequestMethod.GET,
    produces = {"video/mov"}
  )
  public @ResponseBody byte[] videoMovSize(
      @ApiParam(value = "Mov file size", allowableValues = "700KB, 800KB, 1.4MB, 2.2MB")
          @PathVariable("size")
          String size)
      throws IOException {
    return getFileContent("video", "mov", size);
  }

  @ApiOperation(value = "File video mp4 file operation", response = byte[].class)
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")
    }
  )
  @RequestMapping(
    value = "/video/mp4/{size}",
    method = RequestMethod.GET,
    produces = {"video/mp4"}
  )
  public @ResponseBody byte[] videoMp4Size(
      @ApiParam(value = "Mp4 file size", allowableValues = "1.5MB, 3MB, 10MB, 18MB")
          @PathVariable("size")
          String size)
      throws IOException {
    return getFileContent("video", "mp4", size);
  }

  @ApiOperation(value = "File video ogg file operation", response = byte[].class)
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")
    }
  )
  @RequestMapping(
    value = "/video/ogg/{size}",
    method = RequestMethod.GET,
    produces = {"video/ogg"}
  )
  public @ResponseBody byte[] videoOggSize(
      @ApiParam(value = "Ogg file size", allowableValues = "1.7MB, 2.7MB, 11.4MB, 13.3MB")
          @PathVariable("size")
          String size)
      throws IOException {
    return getFileContent("video", "ogg", size);
  }

  @ApiOperation(value = "File video wmv file operation", response = byte[].class)
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")
    }
  )
  @RequestMapping(
    value = "/video/wmv/{size}",
    method = RequestMethod.GET,
    produces = {"video/wmv"}
  )
  public @ResponseBody byte[] videoWmvSize(
      @ApiParam(value = "Wmv file size", allowableValues = "1.2MB, 1.6MB, 4.9MB, 9.3MB")
          @PathVariable("size")
          String size)
      throws IOException {
    return getFileContent("video", "wmv", size);
  }

  /**
   * *********************************************************************************************************************************************
   */
  @ApiOperation(value = "File audio mp3 file operation", response = byte[].class)
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")
    }
  )
  @RequestMapping(
    value = "/audio/mp3/{size}",
    method = RequestMethod.GET,
    produces = {"audio/mpeg3"}
  )
  public @ResponseBody byte[] audioMp3Size(
      @ApiParam(value = "Mp3 file size", allowableValues = "700KB, 1MB, 2MB, 5MB")
          @PathVariable("size")
          String size)
      throws IOException {
    return getFileContent("audio", "mp3", size);
  }

  @ApiOperation(value = "File audio wav file operation", response = byte[].class)
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")
    }
  )
  @RequestMapping(
    value = "/audio/wav/{size}",
    method = RequestMethod.GET,
    produces = {"audio/wav"}
  )
  public @ResponseBody byte[] audioWavSize(
      @ApiParam(value = "Wav file size", allowableValues = "1MB, 2MB, 5MB, 10MB")
          @PathVariable("size")
          String size)
      throws IOException {
    return getFileContent("audio", "wav", size);
  }

  @ApiOperation(value = "File audio ogg file operation", response = byte[].class)
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")
    }
  )
  @RequestMapping(
    value = "/audio/ogg/{size}",
    method = RequestMethod.GET,
    produces = {"audio/ogg"}
  )
  public @ResponseBody byte[] audioOggSize(
      @ApiParam(value = "Ogg file size", allowableValues = "1MB, 2MB, 5MB") @PathVariable("size")
          String size)
      throws IOException {
    return getFileContent("audio", "ogg", size);
  }

  /**
   * *********************************************************************************************************************************************
   */
  @ApiOperation(value = "File document doc file operation", response = byte[].class)
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")
    }
  )
  @RequestMapping(
    value = "/document/doc/{size}",
    method = RequestMethod.GET,
    produces = {"application/doc"}
  )
  public @ResponseBody byte[] documentDocSize(
      @ApiParam(value = "Doc file size", allowableValues = "100KB, 500KB, 1MB")
          @PathVariable("size")
          String size)
      throws IOException {
    return getFileContent("document", "doc", size);
  }

  @ApiOperation(value = "File document docx file operation", response = byte[].class)
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")
    }
  )
  @RequestMapping(
    value = "/document/docx/{size}",
    method = RequestMethod.GET,
    produces = {"application/doc"}
  )
  public @ResponseBody byte[] documentDocxSize(
      @ApiParam(value = "Docx file size", allowableValues = "100KB, 500KB, 1MB")
          @PathVariable("size")
          String size)
      throws IOException {
    return getFileContent("document", "docx", size);
  }

  @ApiOperation(value = "File document xls file operation", response = byte[].class)
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")
    }
  )
  @RequestMapping(
    value = "/document/xls/{size}",
    method = RequestMethod.GET,
    produces = {"application/xls"}
  )
  public @ResponseBody byte[] documentXlsSize(
      @ApiParam(value = "Xls file size", allowableValues = "100KB, 500KB, 1MB")
          @PathVariable("size")
          String size)
      throws IOException {
    return getFileContent("document", "xls", size);
  }

  @ApiOperation(value = "File document xlsx file operation", response = byte[].class)
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")
    }
  )
  @RequestMapping(
    value = "/document/xlsx/{size}",
    method = RequestMethod.GET,
    produces = {"application/xls"}
  )
  public @ResponseBody byte[] documentXlsxSize(
      @ApiParam(value = "Xlsx file size", allowableValues = "100KB, 500KB, 1MB")
          @PathVariable("size")
          String size)
      throws IOException {
    return getFileContent("document", "xlsx", size);
  }

  @ApiOperation(value = "File document ppt file operation", response = byte[].class)
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")
    }
  )
  @RequestMapping(
    value = "/document/ppt/{size}",
    method = RequestMethod.GET,
    produces = {"application/ppt"}
  )
  public @ResponseBody byte[] documentPptSize(
      @ApiParam(value = "Ppt file size", allowableValues = "100KB, 500KB, 1MB")
          @PathVariable("size")
          String size)
      throws IOException {
    return getFileContent("document", "ppt", size);
  }

  @ApiOperation(value = "File document pdf file operation", response = byte[].class)
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")
    }
  )
  @RequestMapping(
    value = "/document/pdf/{size}",
    method = RequestMethod.GET,
    produces = {"application/pdf"}
  )
  public @ResponseBody byte[] documentPdfSize(
      @ApiParam(value = "Pdf file size", allowableValues = "100KB, 500KB, 1MB")
          @PathVariable("size")
          String size)
      throws IOException {
    return getFileContent("document", "pdf", size);
  }

  @ApiOperation(value = "File document odp file operation", response = byte[].class)
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")
    }
  )
  @RequestMapping(
    value = "/document/odp/{size}",
    method = RequestMethod.GET,
    produces = {"application/odp"}
  )
  public @ResponseBody byte[] documentOdpSize(
      @ApiParam(value = "Odp file size", allowableValues = "100KB, 500KB, 1MB")
          @PathVariable("size")
          String size)
      throws IOException {
    return getFileContent("document", "odp", size);
  }

  @ApiOperation(value = "File document ods file operation", response = byte[].class)
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")
    }
  )
  @RequestMapping(
    value = "/document/ods/{size}",
    method = RequestMethod.GET,
    produces = {"application/ods"}
  )
  public @ResponseBody byte[] documentOdsSize(
      @ApiParam(value = "Ods file size", allowableValues = "100KB, 500KB, 1MB")
          @PathVariable("size")
          String size)
      throws IOException {
    return getFileContent("document", "ods", size);
  }

  @ApiOperation(value = "File document odt file operation", response = byte[].class)
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")
    }
  )
  @RequestMapping(
    value = "/document/odt/{size}",
    method = RequestMethod.GET,
    produces = {"application/odt"}
  )
  public @ResponseBody byte[] documentOdtSize(
      @ApiParam(value = "Odt file size", allowableValues = "100KB, 500KB, 1MB")
          @PathVariable("size")
          String size)
      throws IOException {
    return getFileContent("document", "odt", size);
  }

  @ApiOperation(value = "File document rtf file operation", response = byte[].class)
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")
    }
  )
  @RequestMapping(
    value = "/document/rtf/{size}",
    method = RequestMethod.GET,
    produces = {"application/rtf"}
  )
  public @ResponseBody byte[] documentRtfSize(
      @ApiParam(value = "Rtf file size", allowableValues = "100KB, 500KB, 1MB")
          @PathVariable("size")
          String size)
      throws IOException {
    return getFileContent("document", "rtf", size);
  }

  /**
   * *********************************************************************************************************************************************
   */
  @ApiOperation(value = "File other csv file operation", response = byte[].class)
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")
    }
  )
  @RequestMapping(
    value = "/other/csv/{size}",
    method = RequestMethod.GET,
    produces = {"text/csv"}
  )
  public @ResponseBody byte[] otherCsvSize(
      @ApiParam(value = "Csv file size", allowableValues = "7KB") @PathVariable("size") String size)
      throws IOException {
    return getFileContent("other", "csv", size);
  }

  @ApiOperation(value = "File other html file operation", response = byte[].class)
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")
    }
  )
  @RequestMapping(
    value = "/other/html/{size}",
    method = RequestMethod.GET,
    produces = {"text/html"}
  )
  public @ResponseBody byte[] otherHtmlSize(
      @ApiParam(value = "Html file size", allowableValues = "4KB") @PathVariable("size")
          String size)
      throws IOException {
    return getFileContent("other", "html", size);
  }

  @ApiOperation(value = "File other txt file operation", response = byte[].class)
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")
    }
  )
  @RequestMapping(
    value = "/other/txt/{size}",
    method = RequestMethod.GET,
    produces = {"text/plain"}
  )
  public @ResponseBody byte[] otherTxtSize(
      @ApiParam(
            value = "Txt file size",
            allowableValues = "1B, 10B, 100B, 1KB, 10KB, 100KB, 1MB, 10MB"
          )
          @PathVariable("size")
          String size)
      throws IOException {
    return getFileContent("other", "txt", size);
  }

  @ApiOperation(value = "File other zip file operation", response = byte[].class)
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 500, message = "Internal server error")
    }
  )
  @RequestMapping(
    value = "/other/zip/{size}",
    method = RequestMethod.GET,
    produces = {"application/zip"}
  )
  public @ResponseBody byte[] otherZipSize(
      @ApiParam(value = "Zip file size", allowableValues = "2MB, 5MB, 9MB, 10MB")
          @PathVariable("size")
          String size)
      throws IOException {
    return getFileContent("other", "zip", size);
  }
  /**
   * *********************************************************************************************************************************************
   */
}
