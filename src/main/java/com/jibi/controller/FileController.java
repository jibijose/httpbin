package com.jibi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/** The type File controller. */
@Tag(name = "File Api", description = "File Api")
@RestController(value = "File Api")
@RequestMapping("/file")
public class FileController {
  /** The Filegrouptypes. */
  static final Map<String, List<String>> FILEGROUPTYPES = new HashMap<>();
  /** The Filetypesizes. */
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
   *
   * @param fileType the file type
   * @return the byte [ ]
   * @throws IOException the io exception
   */
  @Operation(
      summary = "File api",
      description = "File image file operation",
      tags = {"file"})
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
      name = "fileType",
      schema =
          @Schema(
              description = "File type",
              type = "string",
              allowableValues = {"random", "jpg", "gif", "png", "tiff", "ico"}))
  @RequestMapping(
      value = "/image/{fileType}",
      method = RequestMethod.GET,
      produces = {
        MediaType.IMAGE_JPEG_VALUE,
        MediaType.IMAGE_GIF_VALUE,
        MediaType.IMAGE_PNG_VALUE,
        "image/tiff",
        "image/x-icon"
      })
  public @ResponseBody byte[] imageFileType(@PathVariable String fileType) throws IOException {
    return getFileContent("image", fileType);
  }

  /**
   * Video file type byte [ ].
   *
   * @param fileType the file type
   * @return the byte [ ]
   * @throws IOException the io exception
   */
  @Operation(
      summary = "File api",
      description = "File video file operation",
      tags = {"file"})
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
      name = "fileType",
      schema =
          @Schema(
              description = "File type",
              type = "string",
              allowableValues = {"avi", "mov", "mp4", "png", "ogg", "wmv"}))
  @RequestMapping(
      value = "/video/{fileType}",
      method = RequestMethod.GET,
      produces = {"video/avi", "video/mov", "video/mp4", "video/ogg", "video/wmv"})
  public @ResponseBody byte[] videoFileType(@PathVariable String fileType) throws IOException {
    return getFileContent("video", fileType);
  }

  /**
   * Audio file type byte [ ].
   *
   * @param fileType the file type
   * @return the byte [ ]
   * @throws IOException the io exception
   */
  @Operation(
      summary = "File api",
      description = "File audio file operation",
      tags = {"file"})
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
      name = "fileType",
      schema =
          @Schema(
              description = "File type",
              type = "string",
              allowableValues = {"random", "mp3", "wav", "ogg"}))
  @RequestMapping(
      value = "/audio/{fileType}",
      method = RequestMethod.GET,
      produces = {"audio/mpeg3", "audio/wav", "audio/ogg"})
  public @ResponseBody byte[] audioFileType(@PathVariable String fileType) throws IOException {
    return getFileContent("audio", fileType);
  }

  /**
   * Document file type byte [ ].
   *
   * @param fileType the file type
   * @return the byte [ ]
   * @throws IOException the io exception
   */
  @Operation(
      summary = "File api",
      description = "File document file operation",
      tags = {"file"})
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
      name = "fileType",
      schema =
          @Schema(
              description = "File type",
              type = "string",
              allowableValues = {
                "random", "doc", "docx", "xls", "xlsx", "ppt", "pdf", "odt", "ods", "odp", "rtf"
              }))
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
      })
  public @ResponseBody byte[] documentFileType(@PathVariable String fileType) throws IOException {
    return getFileContent("document", fileType);
  }

  /**
   * Other file type byte [ ].
   *
   * @param fileType the file type
   * @return the byte [ ]
   * @throws IOException the io exception
   */
  @Operation(
      summary = "File api",
      description = "File other file operation",
      tags = {"file"})
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
      name = "fileType",
      schema =
          @Schema(
              description = "File type",
              type = "string",
              allowableValues = {"csv", "html", "txt", "zip"}))
  @RequestMapping(
      value = "/other/{fileType}",
      method = RequestMethod.GET,
      produces = {"text/csv", "text/html", "text/plain", "application/zip"})
  public @ResponseBody byte[] otherFileType(@PathVariable String fileType) throws IOException {
    return getFileContent("other", fileType);
  }

  /**
   * *********************************************************************************************************************************************
   *
   * @param size the size
   * @return the byte [ ]
   * @throws IOException the io exception
   */
  @Operation(
      summary = "File api",
      description = "File image jpg file operation",
      tags = {"file"})
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
              description = "Jpg file size",
              type = "string",
              allowableValues = {"random", "100KB", "500KB", "1MB", "2.5MB"}))
  @RequestMapping(
      value = "/image/jpg/{size}",
      method = RequestMethod.GET,
      produces = {MediaType.IMAGE_JPEG_VALUE})
  public @ResponseBody byte[] imageJpgSize(@PathVariable String size) throws IOException {
    return getFileContent("image", "jpg", size);
  }

  /**
   * Image gif size byte [ ].
   *
   * @param size the size
   * @return the byte [ ]
   * @throws IOException the io exception
   */
  @Operation(
      summary = "File api",
      description = "File image gif file operation",
      tags = {"file"})
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
              description = "Gif file size",
              type = "string",
              allowableValues = {"500KB", "1MB", "3.5MB"}))
  @RequestMapping(
      value = "/image/gif/{size}",
      method = RequestMethod.GET,
      produces = {MediaType.IMAGE_GIF_VALUE})
  public @ResponseBody byte[] imageGifSize(@PathVariable String size) throws IOException {
    return getFileContent("image", "gif", size);
  }

  /**
   * Image png size byte [ ].
   *
   * @param size the size
   * @return the byte [ ]
   * @throws IOException the io exception
   */
  @Operation(
      summary = "File api",
      description = "File image png file operation",
      tags = {"file"})
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
              description = "Png file size",
              type = "string",
              allowableValues = {"500KB", "1MB", "2MB", "3MB"}))
  @RequestMapping(
      value = "/image/png/{size}",
      method = RequestMethod.GET,
      produces = {MediaType.IMAGE_PNG_VALUE})
  public @ResponseBody byte[] imagePngSize(@PathVariable String size) throws IOException {
    return getFileContent("image", "png", size);
  }

  /**
   * Image tiff size byte [ ].
   *
   * @param size the size
   * @return the byte [ ]
   * @throws IOException the io exception
   */
  @Operation(
      summary = "File api",
      description = "File image tiff file operation",
      tags = {"file"})
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
              description = "Tiff file size",
              type = "string",
              allowableValues = {"1MB", "5MB", "10MB"}))
  @RequestMapping(
      value = "/image/tiff/{size}",
      method = RequestMethod.GET,
      produces = {"image/tiff"})
  public @ResponseBody byte[] imageTiffSize(@PathVariable String size) throws IOException {
    return getFileContent("image", "tiff", size);
  }

  /**
   * Image ico size byte [ ].
   *
   * @param size the size
   * @return the byte [ ]
   * @throws IOException the io exception
   */
  @Operation(
      summary = "File api",
      description = "File image ico file operation",
      tags = {"file"})
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
              description = "Ico file size",
              type = "string",
              allowableValues = {"400B"}))
  @RequestMapping(
      value = "/image/ico/{size}",
      method = RequestMethod.GET,
      produces = {"image/x-icon"})
  public @ResponseBody byte[] imageIcoSize(@PathVariable String size) throws IOException {
    return getFileContent("image", "ico", size);
  }

  /**
   * *********************************************************************************************************************************************
   *
   * @param size the size
   * @return the byte [ ]
   * @throws IOException the io exception
   */
  @Operation(
      summary = "File api",
      description = "File video avi file operation",
      tags = {"file"})
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
              description = "Avi file size",
              type = "string",
              allowableValues = {"random", "750KB", "800KB", "1.5MB", "2.3MB"}))
  @RequestMapping(
      value = "/video/avi/{size}",
      method = RequestMethod.GET,
      produces = {"video/avi"})
  public @ResponseBody byte[] videoAviSize(@PathVariable String size) throws IOException {
    return getFileContent("video", "avi", size);
  }

  /**
   * Video mov size byte [ ].
   *
   * @param size the size
   * @return the byte [ ]
   * @throws IOException the io exception
   */
  @Operation(
      summary = "File api",
      description = "File video mov file operation",
      tags = {"file"})
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
              description = "Mov file size",
              type = "string",
              allowableValues = {"700KB", "800Mb", "1.4MB", "2.2MB"}))
  @RequestMapping(
      value = "/video/mov/{size}",
      method = RequestMethod.GET,
      produces = {"video/mov"})
  public @ResponseBody byte[] videoMovSize(@PathVariable String size) throws IOException {
    return getFileContent("video", "mov", size);
  }

  /**
   * Video mp 4 size byte [ ].
   *
   * @param size the size
   * @return the byte [ ]
   * @throws IOException the io exception
   */
  @Operation(
      summary = "File api",
      description = "File video mp4 file operation",
      tags = {"file"})
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
              description = "Mp4 file size",
              type = "string",
              allowableValues = {"1.5MB", "3MB", "10MB", "18MB"}))
  @RequestMapping(
      value = "/video/mp4/{size}",
      method = RequestMethod.GET,
      produces = {"video/mp4"})
  public @ResponseBody byte[] videoMp4Size(@PathVariable String size) throws IOException {
    return getFileContent("video", "mp4", size);
  }

  /**
   * Video ogg size byte [ ].
   *
   * @param size the size
   * @return the byte [ ]
   * @throws IOException the io exception
   */
  @Operation(
      summary = "File api",
      description = "File video ogg file operation",
      tags = {"file"})
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
              description = "Ogg file size",
              type = "string",
              allowableValues = {"1.7MB", "2.7MB", "11.4MB", "13.3MB"}))
  @RequestMapping(
      value = "/video/ogg/{size}",
      method = RequestMethod.GET,
      produces = {"video/ogg"})
  public @ResponseBody byte[] videoOggSize(@PathVariable String size) throws IOException {
    return getFileContent("video", "ogg", size);
  }

  /**
   * Video wmv size byte [ ].
   *
   * @param size the size
   * @return the byte [ ]
   * @throws IOException the io exception
   */
  @Operation(
      summary = "File api",
      description = "File video wmv file operation",
      tags = {"file"})
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
              description = "Wmv file size",
              type = "string",
              allowableValues = {"1.2MB", "1.6MB", "4.9MB", "9.3MB"}))
  @RequestMapping(
      value = "/video/wmv/{size}",
      method = RequestMethod.GET,
      produces = {"video/wmv"})
  public @ResponseBody byte[] videoWmvSize(@PathVariable String size) throws IOException {
    return getFileContent("video", "wmv", size);
  }

  /**
   * *********************************************************************************************************************************************
   *
   * @param size the size
   * @return the byte [ ]
   * @throws IOException the io exception
   */
  @Operation(
      summary = "File api",
      description = "File audio mp3 file operation",
      tags = {"file"})
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
              description = "Mp3 file size",
              type = "string",
              allowableValues = {"700KB", "1MB", "2MB", "5MB"}))
  @RequestMapping(
      value = "/audio/mp3/{size}",
      method = RequestMethod.GET,
      produces = {"audio/mpeg3"})
  public @ResponseBody byte[] audioMp3Size(@PathVariable String size) throws IOException {
    return getFileContent("audio", "mp3", size);
  }

  /**
   * Audio wav size byte [ ].
   *
   * @param size the size
   * @return the byte [ ]
   * @throws IOException the io exception
   */
  @Operation(
      summary = "File api",
      description = "File audio wav file operation",
      tags = {"file"})
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
              description = "Wav file size",
              type = "string",
              allowableValues = {"1MB", "2MB", "5MB", "10MB"}))
  @RequestMapping(
      value = "/audio/wav/{size}",
      method = RequestMethod.GET,
      produces = {"audio/wav"})
  public @ResponseBody byte[] audioWavSize(@PathVariable String size) throws IOException {
    return getFileContent("audio", "wav", size);
  }

  /**
   * Audio ogg size byte [ ].
   *
   * @param size the size
   * @return the byte [ ]
   * @throws IOException the io exception
   */
  @Operation(
      summary = "File api",
      description = "File audio ogg file operation",
      tags = {"file"})
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
              description = "Ogg file size",
              type = "string",
              allowableValues = {"1MB", "2MB", "5MB"}))
  @RequestMapping(
      value = "/audio/ogg/{size}",
      method = RequestMethod.GET,
      produces = {"audio/ogg"})
  public @ResponseBody byte[] audioOggSize(@PathVariable String size) throws IOException {
    return getFileContent("audio", "ogg", size);
  }

  /**
   * *********************************************************************************************************************************************
   *
   * @param size the size
   * @return the byte [ ]
   * @throws IOException the io exception
   */
  @Operation(
      summary = "File api",
      description = "File document doc file operation",
      tags = {"file"})
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
              description = "Doc file size",
              type = "string",
              allowableValues = {"100KB", "500KB", "1MB"}))
  @RequestMapping(
      value = "/document/doc/{size}",
      method = RequestMethod.GET,
      produces = {"application/doc"})
  public @ResponseBody byte[] documentDocSize(@PathVariable String size) throws IOException {
    return getFileContent("document", "doc", size);
  }

  /**
   * Document docx size byte [ ].
   *
   * @param size the size
   * @return the byte [ ]
   * @throws IOException the io exception
   */
  @Operation(
      summary = "File api",
      description = "File document docx file operation",
      tags = {"file"})
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
              description = "Docx file size",
              type = "string",
              allowableValues = {"100KB", "500KB", "1MB"}))
  @RequestMapping(
      value = "/document/docx/{size}",
      method = RequestMethod.GET,
      produces = {"application/doc"})
  public @ResponseBody byte[] documentDocxSize(@PathVariable String size) throws IOException {
    return getFileContent("document", "docx", size);
  }

  /**
   * Document xls size byte [ ].
   *
   * @param size the size
   * @return the byte [ ]
   * @throws IOException the io exception
   */
  @Operation(
      summary = "File api",
      description = "File document xls file operation",
      tags = {"file"})
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
              description = "Xls file size",
              type = "string",
              allowableValues = {"100KB", "500KB", "1MB"}))
  @RequestMapping(
      value = "/document/xls/{size}",
      method = RequestMethod.GET,
      produces = {"application/xls"})
  public @ResponseBody byte[] documentXlsSize(@PathVariable String size) throws IOException {
    return getFileContent("document", "xls", size);
  }

  /**
   * Document xlsx size byte [ ].
   *
   * @param size the size
   * @return the byte [ ]
   * @throws IOException the io exception
   */
  @Operation(
      summary = "File api",
      description = "File document xlsx file operation",
      tags = {"file"})
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
              description = "Xlsx file size",
              type = "string",
              allowableValues = {"100KB", "500KB", "1MB"}))
  @RequestMapping(
      value = "/document/xlsx/{size}",
      method = RequestMethod.GET,
      produces = {"application/xls"})
  public @ResponseBody byte[] documentXlsxSize(@PathVariable String size) throws IOException {
    return getFileContent("document", "xlsx", size);
  }

  /**
   * Document ppt size byte [ ].
   *
   * @param size the size
   * @return the byte [ ]
   * @throws IOException the io exception
   */
  @Operation(
      summary = "File api",
      description = "File document ppt file operation",
      tags = {"file"})
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
              description = "Ppt file size",
              type = "string",
              allowableValues = {"100KB", "500KB", "1MB"}))
  @RequestMapping(
      value = "/document/ppt/{size}",
      method = RequestMethod.GET,
      produces = {"application/ppt"})
  public @ResponseBody byte[] documentPptSize(@PathVariable String size) throws IOException {
    return getFileContent("document", "ppt", size);
  }

  /**
   * Document pdf size byte [ ].
   *
   * @param size the size
   * @return the byte [ ]
   * @throws IOException the io exception
   */
  @Operation(
      summary = "File api",
      description = "File document pdf file operation",
      tags = {"file"})
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
              description = "Pdf file size",
              type = "string",
              allowableValues = {"100KB", "500KB", "1MB"}))
  @RequestMapping(
      value = "/document/pdf/{size}",
      method = RequestMethod.GET,
      produces = {"application/pdf"})
  public @ResponseBody byte[] documentPdfSize(@PathVariable String size) throws IOException {
    return getFileContent("document", "pdf", size);
  }

  /**
   * Document odp size byte [ ].
   *
   * @param size the size
   * @return the byte [ ]
   * @throws IOException the io exception
   */
  @Operation(
      summary = "File api",
      description = "File document odp file operation",
      tags = {"file"})
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
              description = "Odp file size",
              type = "string",
              allowableValues = {"100KB", "500KB", "1MB"}))
  @RequestMapping(
      value = "/document/odp/{size}",
      method = RequestMethod.GET,
      produces = {"application/odp"})
  public @ResponseBody byte[] documentOdpSize(@PathVariable String size) throws IOException {
    return getFileContent("document", "odp", size);
  }

  /**
   * Document ods size byte [ ].
   *
   * @param size the size
   * @return the byte [ ]
   * @throws IOException the io exception
   */
  @Operation(
      summary = "File api",
      description = "File document ods file operation",
      tags = {"file"})
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
              description = "Ods file size",
              type = "string",
              allowableValues = {"100KB", "500KB", "1MB"}))
  @RequestMapping(
      value = "/document/ods/{size}",
      method = RequestMethod.GET,
      produces = {"application/ods"})
  public @ResponseBody byte[] documentOdsSize(@PathVariable String size) throws IOException {
    return getFileContent("document", "ods", size);
  }

  /**
   * Document odt size byte [ ].
   *
   * @param size the size
   * @return the byte [ ]
   * @throws IOException the io exception
   */
  @Operation(
      summary = "File api",
      description = "File document odt file operation",
      tags = {"file"})
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
              description = "Odt file size",
              type = "string",
              allowableValues = {"100KB", "500KB", "1MB"}))
  @RequestMapping(
      value = "/document/odt/{size}",
      method = RequestMethod.GET,
      produces = {"application/odt"})
  public @ResponseBody byte[] documentOdtSize(@PathVariable String size) throws IOException {
    return getFileContent("document", "odt", size);
  }

  /**
   * Document rtf size byte [ ].
   *
   * @param size the size
   * @return the byte [ ]
   * @throws IOException the io exception
   */
  @Operation(
      summary = "File api",
      description = "File document rtf file operation",
      tags = {"file"})
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
              description = "Rtf file size",
              type = "string",
              allowableValues = {"100KB", "500KB", "1MB"}))
  @RequestMapping(
      value = "/document/rtf/{size}",
      method = RequestMethod.GET,
      produces = {"application/rtf"})
  public @ResponseBody byte[] documentRtfSize(@PathVariable String size) throws IOException {
    return getFileContent("document", "rtf", size);
  }

  /**
   * *********************************************************************************************************************************************
   *
   * @param size the size
   * @return the byte [ ]
   * @throws IOException the io exception
   */
  @Operation(
      summary = "File api",
      description = "File other csv file operation",
      tags = {"file"})
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
              description = "Csv file size",
              type = "string",
              allowableValues = {"7KB"}))
  @RequestMapping(
      value = "/other/csv/{size}",
      method = RequestMethod.GET,
      produces = {"text/csv"})
  public @ResponseBody byte[] otherCsvSize(@PathVariable String size) throws IOException {
    return getFileContent("other", "csv", size);
  }

  /**
   * Other html size byte [ ].
   *
   * @param size the size
   * @return the byte [ ]
   * @throws IOException the io exception
   */
  @Operation(
      summary = "File api",
      description = "File other html file operation",
      tags = {"file"})
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
              description = "Html file size",
              type = "string",
              allowableValues = {"4KB"}))
  @RequestMapping(
      value = "/other/html/{size}",
      method = RequestMethod.GET,
      produces = {"text/html"})
  public @ResponseBody byte[] otherHtmlSize(@PathVariable String size) throws IOException {
    return getFileContent("other", "html", size);
  }

  /**
   * Other txt size byte [ ].
   *
   * @param size the size
   * @return the byte [ ]
   * @throws IOException the io exception
   */
  @Operation(
      summary = "File api",
      description = "File other txt file operation",
      tags = {"file"})
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
              description = "Txt file size",
              type = "string",
              allowableValues = {"1B", "10B", "100B", "1KB", "10KB", "100KB", "1MB", "10MB"}))
  @RequestMapping(
      value = "/other/txt/{size}",
      method = RequestMethod.GET,
      produces = {"text/plain"})
  public @ResponseBody byte[] otherTxtSize(@PathVariable String size) throws IOException {
    return getFileContent("other", "txt", size);
  }

  /**
   * Other zip size byte [ ].
   *
   * @param size the size
   * @return the byte [ ]
   * @throws IOException the io exception
   */
  @Operation(
      summary = "File api",
      description = "File other zip file operation",
      tags = {"file"})
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
              description = "zip file size",
              type = "string",
              allowableValues = {"2MB", "5MB", "9MB", "10MB"}))
  @RequestMapping(
      value = "/other/zip/{size}",
      method = RequestMethod.GET,
      produces = {"application/zip"})
  public @ResponseBody byte[] otherZipSize(@PathVariable String size) throws IOException {
    return getFileContent("other", "zip", size);
  }
  /**
   * *********************************************************************************************************************************************
   */
}
