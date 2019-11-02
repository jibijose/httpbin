package com.jibi.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FileControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    /************************************************************************************************************************************************/

    @Test
    public void testFileImage() throws Exception {
        fileTypeTests("image");
    }

    @Test
    public void testFileVideo() throws Exception {
        fileTypeTests("video");
    }

    @Test
    public void testFileAudio() throws Exception {
        fileTypeTests("audio");
    }

    @Test
    public void testFileDocument() throws Exception {
        fileTypeTests("document");
    }

    @Test
    public void testFileOther() throws Exception {
        fileTypeTests("other");
    }

    /************************************************************************************************************************************************/

    @Test
    public void testFileImageJpgSize() throws Exception {
        fileTypeSizeTests("image", "jpg");
    }

    @Test
    public void testFileImageGifSize() throws Exception {
        fileTypeSizeTests("image", "gif");
    }

    @Test
    public void testFileImagePngSize() throws Exception {
        fileTypeSizeTests("image", "png");
    }

    @Test
    public void testFileImageTiffSize() throws Exception {
        fileTypeSizeTests("image", "tiff");
    }

    @Test
    public void testFileImageIcoSize() throws Exception {
        fileTypeSizeTests("image", "ico");
    }

    /************************************************************************************************************************************************/

    @Test
    public void testFileVideoAviSize() throws Exception {
        fileTypeSizeTests("video", "avi");
    }

    @Test
    public void testFileVideoMovSize() throws Exception {
        fileTypeSizeTests("video", "mov");
    }

    @Test
    public void testFileVideoMp4Size() throws Exception {
        fileTypeSizeTests("video", "mp4");
    }

    @Test
    public void testFileVideoOggSize() throws Exception {
        fileTypeSizeTests("video", "ogg");
    }

    @Test
    public void testFileVideoWmvSize() throws Exception {
        fileTypeSizeTests("video", "wmv");
    }

    /************************************************************************************************************************************************/

    @Test
    public void testFileAudioMp3Size() throws Exception {
        fileTypeSizeTests("audio", "mp3");
    }

    @Test
    public void testFileAudioWavSize() throws Exception {
        fileTypeSizeTests("audio", "wav");
    }

    @Test
    public void testFileAudioOggSize() throws Exception {
        fileTypeSizeTests("audio", "ogg");
    }

    /************************************************************************************************************************************************/

    @Test
    public void testFileDocumentDocSize() throws Exception {
        fileTypeSizeTests("document", "doc");
    }

    @Test
    public void testFileDocumentDocxSize() throws Exception {
        fileTypeSizeTests("document", "docx");
    }

    @Test
    public void testFileDocumentXlsSize() throws Exception {
        fileTypeSizeTests("document", "xls");
    }

    @Test
    public void testFileDocumentXlsxSize() throws Exception {
        fileTypeSizeTests("document", "xlsx");
    }

    @Test
    public void testFileDocumentPptSize() throws Exception {
        fileTypeSizeTests("document", "ppt");
    }

    @Test
    public void testFileDocumentPdfSize() throws Exception {
        fileTypeSizeTests("document", "pdf");
    }

    @Test
    public void testFileDocumentOdpSize() throws Exception {
        fileTypeSizeTests("document", "odp");
    }

    @Test
    public void testFileDocumentOdsSize() throws Exception {
        fileTypeSizeTests("document", "ods");
    }

    @Test
    public void testFileDocumentOdtSize() throws Exception {
        fileTypeSizeTests("document", "odt");
    }

    @Test
    public void testFileDocumentRtfSize() throws Exception {
        fileTypeSizeTests("document", "rtf");
    }

    /************************************************************************************************************************************************/

    @Test
    public void testFileOtherCsvSize() throws Exception {
        fileTypeSizeTests("other", "csv");
    }

    @Test
    public void testFileOtherHtmlSize() throws Exception {
        fileTypeSizeTests("other", "html");
    }

    @Test
    public void testFileOtherTxtSize() throws Exception {
        fileTypeSizeTests("other", "txt");
    }

    @Test
    public void testFileOtherZipSize() throws Exception {
        fileTypeSizeTests("other", "zip");
    }

    /************************************************************************************************************************************************/

    private void fileTypeTests(String fileGroup) {
        ResponseEntity<byte[]> responseRandom = this.restTemplate.getForEntity("http://localhost:" + port + "/file/" + fileGroup + "/" + "random", byte[].class);
        assertEquals(responseRandom.getStatusCode(), HttpStatus.OK);

        FileController.FILEGROUPTYPES.get(fileGroup).stream().forEach(fileTypeName -> {
            ResponseEntity<byte[]> response = this.restTemplate.getForEntity("http://localhost:" + port + "/file/" + fileGroup + "/" + fileTypeName, byte[].class);
            assertEquals(response.getStatusCode(), HttpStatus.OK);
        });
    }

    private void fileTypeSizeTests(String fileGroup, String fileType) {
        ResponseEntity<byte[]> responseRandom = this.restTemplate.getForEntity("http://localhost:" + port + "/file/" + fileGroup + "/" + fileType + "/" + "random", byte[].class);
        assertEquals(responseRandom.getStatusCode(), HttpStatus.OK);

        FileController.FILETYPESIZES.get(fileGroup + "_" + fileType).stream().forEach(size -> {
            ResponseEntity<byte[]> response = this.restTemplate.getForEntity("http://localhost:" + port + "/file/" + fileGroup + "/" + fileType + "/" + size, byte[].class);
            assertEquals(response.getStatusCode(), HttpStatus.OK);
        });
    }

    /************************************************************************************************************************************************/

}
