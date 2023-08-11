package com.jibi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
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
   *
   * @throws Exception the exception
   */
  @Test
  void testFileImage(ApplicationContext context) throws Exception {
    fileTypeTests("image");
    assertNotNull(context);
  }

  /**
   * Test file video.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileVideo(ApplicationContext context) throws Exception {
    fileTypeTests("video");
    assertNotNull(context);
  }

  /**
   * Test file audio.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileAudio(ApplicationContext context) throws Exception {
    fileTypeTests("audio");
    assertNotNull(context);
  }

  /**
   * Test file document.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileDocument(ApplicationContext context) throws Exception {
    fileTypeTests("document");
    assertNotNull(context);
  }

  /**
   * Test file other.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileOther(ApplicationContext context) throws Exception {
    fileTypeTests("other");
    assertNotNull(context);
  }

  /**
   * *********************************************************************************************************************************************
   *
   * @throws Exception the exception
   */
  @Test
  void testFileImageJpgSize(ApplicationContext context) throws Exception {
    fileTypeSizeTests("image", "jpg");
    assertNotNull(context);
  }

  /**
   * Test file image gif size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileImageGifSize(ApplicationContext context) throws Exception {
    fileTypeSizeTests("image", "gif");
    assertNotNull(context);
  }

  /**
   * Test file image png size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileImagePngSize(ApplicationContext context) throws Exception {
    fileTypeSizeTests("image", "png");
    assertNotNull(context);
  }

  /**
   * Test file image tiff size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileImageTiffSize(ApplicationContext context) throws Exception {
    fileTypeSizeTests("image", "tiff");
    assertNotNull(context);
  }

  /**
   * Test file image ico size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileImageIcoSize(ApplicationContext context) throws Exception {
    fileTypeSizeTests("image", "ico");
    assertNotNull(context);
  }

  /**
   * *********************************************************************************************************************************************
   *
   * @throws Exception the exception
   */
  @Test
  void testFileVideoAviSize(ApplicationContext context) throws Exception {
    fileTypeSizeTests("video", "avi");
    assertNotNull(context);
  }

  /**
   * Test file video mov size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileVideoMovSize(ApplicationContext context) throws Exception {
    fileTypeSizeTests("video", "mov");
    assertNotNull(context);
  }

  /**
   * Test file video mp 4 size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileVideoMp4Size(ApplicationContext context) throws Exception {
    fileTypeSizeTests("video", "mp4");
    assertNotNull(context);
  }

  /**
   * Test file video ogg size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileVideoOggSize(ApplicationContext context) throws Exception {
    fileTypeSizeTests("video", "ogg");
    assertNotNull(context);
  }

  /**
   * Test file video wmv size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileVideoWmvSize(ApplicationContext context) throws Exception {
    fileTypeSizeTests("video", "wmv");
    assertNotNull(context);
  }

  /**
   * *********************************************************************************************************************************************
   *
   * @throws Exception the exception
   */
  @Test
  void testFileAudioMp3Size(ApplicationContext context) throws Exception {
    fileTypeSizeTests("audio", "mp3");
    assertNotNull(context);
  }

  /**
   * Test file audio wav size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileAudioWavSize(ApplicationContext context) throws Exception {
    fileTypeSizeTests("audio", "wav");
    assertNotNull(context);
  }

  /**
   * Test file audio ogg size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileAudioOggSize(ApplicationContext context) throws Exception {
    fileTypeSizeTests("audio", "ogg");
    assertNotNull(context);
  }

  /**
   * *********************************************************************************************************************************************
   *
   * @throws Exception the exception
   */
  @Test
  void testFileDocumentDocSize(ApplicationContext context) throws Exception {
    fileTypeSizeTests("document", "doc");
    assertNotNull(context);
  }

  /**
   * Test file document docx size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileDocumentDocxSize(ApplicationContext context) throws Exception {
    fileTypeSizeTests("document", "docx");
    assertNotNull(context);
  }

  /**
   * Test file document xls size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileDocumentXlsSize(ApplicationContext context) throws Exception {
    fileTypeSizeTests("document", "xls");
    assertNotNull(context);
  }

  /**
   * Test file document xlsx size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileDocumentXlsxSize(ApplicationContext context) throws Exception {
    fileTypeSizeTests("document", "xlsx");
    assertNotNull(context);
  }

  /**
   * Test file document ppt size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileDocumentPptSize(ApplicationContext context) throws Exception {
    fileTypeSizeTests("document", "ppt");
    assertNotNull(context);
  }

  /**
   * Test file document pdf size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileDocumentPdfSize(ApplicationContext context) throws Exception {
    fileTypeSizeTests("document", "pdf");
    assertNotNull(context);
  }

  /**
   * Test file document odp size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileDocumentOdpSize(ApplicationContext context) throws Exception {
    fileTypeSizeTests("document", "odp");
    assertNotNull(context);
  }

  /**
   * Test file document ods size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileDocumentOdsSize(ApplicationContext context) throws Exception {
    fileTypeSizeTests("document", "ods");
    assertNotNull(context);
  }

  /**
   * Test file document odt size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileDocumentOdtSize(ApplicationContext context) throws Exception {
    fileTypeSizeTests("document", "odt");
    assertNotNull(context);
  }

  /**
   * Test file document rtf size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileDocumentRtfSize(ApplicationContext context) throws Exception {
    fileTypeSizeTests("document", "rtf");
    assertNotNull(context);
  }

  /**
   * *********************************************************************************************************************************************
   *
   * @throws Exception the exception
   */
  @Test
  void testFileOtherCsvSize(ApplicationContext context) throws Exception {
    fileTypeSizeTests("other", "csv");
    assertNotNull(context);
  }

  /**
   * Test file other html size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileOtherHtmlSize(ApplicationContext context) throws Exception {
    fileTypeSizeTests("other", "html");
    assertNotNull(context);
  }

  /**
   * Test file other txt size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileOtherTxtSize(ApplicationContext context) throws Exception {
    fileTypeSizeTests("other", "txt");
    assertNotNull(context);
  }

  /**
   * Test file other zip size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileOtherZipSize(ApplicationContext context) throws Exception {
    fileTypeSizeTests("other", "zip");
    assertNotNull(context);
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
