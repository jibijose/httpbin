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
    public void testFileAudio() throws Exception {
        fileTypeTests("audio");
    }

    /************************************************************************************************************************************************/

    @Test
    public void testFileJpgSize() throws Exception {
        fileTypeSizeTests("image", "jpg");
    }

    @Test
    public void testFileGifSize() throws Exception {
        fileTypeSizeTests("image", "gif");
    }

    @Test
    public void testFilePngSize() throws Exception {
        fileTypeSizeTests("image", "png");
    }

    @Test
    public void testFileTiffSize() throws Exception {
        fileTypeSizeTests("image", "tiff");
    }

    @Test
    public void testFileIcoSize() throws Exception {
        fileTypeSizeTests("image", "ico");
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

        FileController.FILETYPESIZES.get(fileType).stream().forEach(size -> {
            ResponseEntity<byte[]> response = this.restTemplate.getForEntity("http://localhost:" + port + "/file/" + fileGroup + "/" + fileType + "/" + size, byte[].class);
            assertEquals(response.getStatusCode(), HttpStatus.OK);
        });
    }

    /************************************************************************************************************************************************/

}
