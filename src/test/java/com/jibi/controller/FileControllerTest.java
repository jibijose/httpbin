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

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FileControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testFileImage() throws Exception {
        fileTypeTests(FileController.IMAGETYPES, "image");
    }

    @Test
    public void testFileJpgSize() throws Exception {
        fileTypeSizeTests(FileController.JPGSIZES, "image", "jpg");
    }

    @Test
    public void testFileGifSize() throws Exception {
        fileTypeSizeTests(FileController.GIFSIZES, "image", "gif");
    }

    @Test
    public void testFilePngSize() throws Exception {
        fileTypeSizeTests(FileController.PNGSIZES, "image", "png");
    }

    @Test
    public void testFileTiffSize() throws Exception {
        fileTypeSizeTests(FileController.TIFFSIZES, "image", "tiff");
    }

    @Test
    public void testFileIcoSize() throws Exception {
        fileTypeSizeTests(FileController.ICOSIZES, "image", "ico");
    }

    private void fileTypeTests(List<String> fileTypeNames, String fileType) {
        ResponseEntity<byte[]> responseRandom = this.restTemplate.getForEntity("http://localhost:" + port + "/file/" + fileType + "/" + "random", byte[].class);
        assertEquals(responseRandom.getStatusCode(), HttpStatus.OK);

        fileTypeNames.stream().forEach(fileTypeName -> {
            ResponseEntity<byte[]> response = this.restTemplate.getForEntity("http://localhost:" + port + "/file/" + fileType + "/" + fileTypeName, byte[].class);
            assertEquals(response.getStatusCode(), HttpStatus.OK);
        });
    }

    private void fileTypeSizeTests(List<String> fileTypeNameSizes, String fileType, String fileTypeName) {
        ResponseEntity<byte[]> responseRandom = this.restTemplate.getForEntity("http://localhost:" + port + "/file/" + fileType + "/" + fileTypeName + "/" + "random", byte[].class);
        assertEquals(responseRandom.getStatusCode(), HttpStatus.OK);

        fileTypeNameSizes.stream().forEach(size -> {
            ResponseEntity<byte[]> response = this.restTemplate.getForEntity("http://localhost:" + port + "/file/" + fileType + "/" + fileTypeName + "/" + size, byte[].class);
            assertEquals(response.getStatusCode(), HttpStatus.OK);
        });
    }

}
