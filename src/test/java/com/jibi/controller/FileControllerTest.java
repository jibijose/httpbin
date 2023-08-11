package com.jibi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/** The type File controller test. */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FileControllerTest {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  /**
   * *********************************************************************************************************************************************
   * Test file.
   */
  @ParameterizedTest
  @ValueSource(strings = {"video", "image", "audio", "document", "other"})
  void testFile(String input, ApplicationContext context) {
    fileTypeTests(input);
    assertNotNull(context);
  }

  /**
   * *********************************************************************************************************************************************
   * Test file image size.
   */
  @ParameterizedTest
  @CsvSource({"image,jpg", "image,gif", "image,png", "image,tiff", "image,ico"})
  void testFileImageSize(String fg, String ft, ApplicationContext ctx) {
    fileTypeSizeTests(fg, ft);
    assertNotNull(ctx);
  }

  /**
   * *********************************************************************************************************************************************
   * Test file video size.
   */
  @ParameterizedTest
  @CsvSource({"video,avi", "video,mov", "video,mp4", "video,ogg", "video,wmv"})
  void testFileVideoSize(String fg, String ft, ApplicationContext ctx) {
    fileTypeSizeTests(fg, ft);
    assertNotNull(ctx);
  }

  /**
   * *********************************************************************************************************************************************
   * Test file audio wav size.
   */
  @ParameterizedTest
  @CsvSource({"audio,mp3", "audio,wav", "audio,ogg"})
  void testFileAudioSize(String fg, String ft, ApplicationContext ctx) {
    fileTypeSizeTests(fg, ft);
    assertNotNull(ctx);
  }

  /**
   * *********************************************************************************************************************************************
   * Test file document size.
   */
  @ParameterizedTest
  @CsvSource({
    "document,doc",
    "document,docx",
    "document,xls",
    "document,xlsx",
    "document,ppt",
    "document,pdf",
    "document,odp",
    "document,ods",
    "document,odt",
    "document,rtf"
  })
  void testFileDocumentSize(String fg, String ft, ApplicationContext ctx) {
    fileTypeSizeTests(fg, ft);
    assertNotNull(ctx);
  }

  /**
   * *********************************************************************************************************************************************
   * Test file other size.
   */
  @ParameterizedTest
  @CsvSource({"other,csv", "other,html", "other,txt", "other,zip"})
  void testFileOtherSize(String fg, String ft, ApplicationContext ctx) {
    fileTypeSizeTests(fg, ft);
    assertNotNull(ctx);
  }

  /**
   * *********************************************************************************************************************************************
   */
  private void fileTypeTests(String fileGroup) {
    ResponseEntity<byte[]> responseRandom =
        this.restTemplate.getForEntity(
            "http://localhost:" + port + "/file/" + fileGroup + "/" + "random", byte[].class);
    assertEquals(responseRandom.getStatusCode(), HttpStatus.OK);

    FileController.FILEGROUPTYPES.get(fileGroup).stream()
        .forEach(
            fileTypeName -> {
              ResponseEntity<byte[]> response =
                  this.restTemplate.getForEntity(
                      "http://localhost:" + port + "/file/" + fileGroup + "/" + fileTypeName,
                      byte[].class);
              assertEquals(response.getStatusCode(), HttpStatus.OK);
            });
  }

  private void fileTypeSizeTests(String fileGroup, String fileType) {
    ResponseEntity<byte[]> responseRandom =
        this.restTemplate.getForEntity(
            "http://localhost:" + port + "/file/" + fileGroup + "/" + fileType + "/" + "random",
            byte[].class);
    assertEquals(responseRandom.getStatusCode(), HttpStatus.OK);

    FileController.FILETYPESIZES.get(fileGroup + "_" + fileType).stream()
        .forEach(
            size -> {
              ResponseEntity<byte[]> response =
                  this.restTemplate.getForEntity(
                      "http://localhost:"
                          + port
                          + "/file/"
                          + fileGroup
                          + "/"
                          + fileType
                          + "/"
                          + size,
                      byte[].class);
              assertEquals(response.getStatusCode(), HttpStatus.OK);
            });
  }

  /**
   * *********************************************************************************************************************************************
   */
}
