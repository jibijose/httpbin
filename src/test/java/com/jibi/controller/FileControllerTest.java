package com.jibi.controller;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
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
  void testFileImage() throws Exception {
    fileTypeTests("image");
  }

  /**
   * Test file video.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileVideo() throws Exception {
    fileTypeTests("video");
  }

  /**
   * Test file audio.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileAudio() throws Exception {
    fileTypeTests("audio");
  }

  /**
   * Test file document.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileDocument() throws Exception {
    fileTypeTests("document");
  }

  /**
   * Test file other.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileOther() throws Exception {
    fileTypeTests("other");
  }

  /**
   * *********************************************************************************************************************************************
   *
   * @throws Exception the exception
   */
  @Test
  void testFileImageJpgSize() throws Exception {
    fileTypeSizeTests("image", "jpg");
  }

  /**
   * Test file image gif size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileImageGifSize() throws Exception {
    fileTypeSizeTests("image", "gif");
  }

  /**
   * Test file image png size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileImagePngSize() throws Exception {
    fileTypeSizeTests("image", "png");
  }

  /**
   * Test file image tiff size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileImageTiffSize() throws Exception {
    fileTypeSizeTests("image", "tiff");
  }

  /**
   * Test file image ico size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileImageIcoSize() throws Exception {
    fileTypeSizeTests("image", "ico");
  }

  /**
   * *********************************************************************************************************************************************
   *
   * @throws Exception the exception
   */
  @Test
  void testFileVideoAviSize() throws Exception {
    fileTypeSizeTests("video", "avi");
  }

  /**
   * Test file video mov size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileVideoMovSize() throws Exception {
    fileTypeSizeTests("video", "mov");
  }

  /**
   * Test file video mp 4 size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileVideoMp4Size() throws Exception {
    fileTypeSizeTests("video", "mp4");
  }

  /**
   * Test file video ogg size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileVideoOggSize() throws Exception {
    fileTypeSizeTests("video", "ogg");
  }

  /**
   * Test file video wmv size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileVideoWmvSize() throws Exception {
    fileTypeSizeTests("video", "wmv");
  }

  /**
   * *********************************************************************************************************************************************
   *
   * @throws Exception the exception
   */
  @Test
  void testFileAudioMp3Size() throws Exception {
    fileTypeSizeTests("audio", "mp3");
  }

  /**
   * Test file audio wav size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileAudioWavSize() throws Exception {
    fileTypeSizeTests("audio", "wav");
  }

  /**
   * Test file audio ogg size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileAudioOggSize() throws Exception {
    fileTypeSizeTests("audio", "ogg");
  }

  /**
   * *********************************************************************************************************************************************
   *
   * @throws Exception the exception
   */
  @Test
  void testFileDocumentDocSize() throws Exception {
    fileTypeSizeTests("document", "doc");
  }

  /**
   * Test file document docx size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileDocumentDocxSize() throws Exception {
    fileTypeSizeTests("document", "docx");
  }

  /**
   * Test file document xls size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileDocumentXlsSize() throws Exception {
    fileTypeSizeTests("document", "xls");
  }

  /**
   * Test file document xlsx size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileDocumentXlsxSize() throws Exception {
    fileTypeSizeTests("document", "xlsx");
  }

  /**
   * Test file document ppt size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileDocumentPptSize() throws Exception {
    fileTypeSizeTests("document", "ppt");
  }

  /**
   * Test file document pdf size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileDocumentPdfSize() throws Exception {
    fileTypeSizeTests("document", "pdf");
  }

  /**
   * Test file document odp size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileDocumentOdpSize() throws Exception {
    fileTypeSizeTests("document", "odp");
  }

  /**
   * Test file document ods size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileDocumentOdsSize() throws Exception {
    fileTypeSizeTests("document", "ods");
  }

  /**
   * Test file document odt size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileDocumentOdtSize() throws Exception {
    fileTypeSizeTests("document", "odt");
  }

  /**
   * Test file document rtf size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileDocumentRtfSize() throws Exception {
    fileTypeSizeTests("document", "rtf");
  }

  /**
   * *********************************************************************************************************************************************
   *
   * @throws Exception the exception
   */
  @Test
  void testFileOtherCsvSize() throws Exception {
    fileTypeSizeTests("other", "csv");
  }

  /**
   * Test file other html size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileOtherHtmlSize() throws Exception {
    fileTypeSizeTests("other", "html");
  }

  /**
   * Test file other txt size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileOtherTxtSize() throws Exception {
    fileTypeSizeTests("other", "txt");
  }

  /**
   * Test file other zip size.
   *
   * @throws Exception the exception
   */
  @Test
  void testFileOtherZipSize() throws Exception {
    fileTypeSizeTests("other", "zip");
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
