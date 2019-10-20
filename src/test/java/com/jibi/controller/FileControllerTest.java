package com.jibi.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FileControllerTest {

    private static List<String> FILETYPES = Arrays.asList("jpg", "gif", "png", "tiff", "ico");
    private static List<String> JPGSIZES = Arrays.asList("100KB", "500KB", "1MB", "2.5MB");
    private static List<String> GIFSIZES = Arrays.asList("500KB", "1MB", "3.5MB");
    private static List<String> PNGSIZES = Arrays.asList("500KB", "1MB", "2MB", "3MB");
    private static List<String> TIFFSIZES = Arrays.asList("1MB", "5MB", "10MB");
    private static List<String> ICOSIZES = Arrays.asList("400B");

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testFileImage() throws Exception {
        FILETYPES.stream().forEach(fileType -> {
            ResponseEntity<byte[]> response = this.restTemplate.getForEntity("http://localhost:" + port + "/file/image/" + fileType, byte[].class);
            assertEquals(response.getStatusCode(), HttpStatus.OK);
        });
    }

    @Test
    public void testFileJpgSize() throws Exception {
        JPGSIZES.stream().forEach(size -> {
            ResponseEntity<byte[]> response = this.restTemplate.getForEntity("http://localhost:" + port + "/file/image/jpg/" + size, byte[].class);
            assertEquals(response.getStatusCode(), HttpStatus.OK);
        });
    }

    @Test
    public void testFileGifSize() throws Exception {
        GIFSIZES.stream().forEach(size -> {
            ResponseEntity<byte[]> response = this.restTemplate.getForEntity("http://localhost:" + port + "/file/image/gif/" + size, byte[].class);
            assertEquals(response.getStatusCode(), HttpStatus.OK);
        });
    }

    @Test
    public void testFilePngSize() throws Exception {
        PNGSIZES.stream().forEach(size -> {
            ResponseEntity<byte[]> response = this.restTemplate.getForEntity("http://localhost:" + port + "/file/image/png/" + size, byte[].class);
            assertEquals(response.getStatusCode(), HttpStatus.OK);
        });
    }

    @Test
    public void testFileTiffSize() throws Exception {
        TIFFSIZES.stream().forEach(size -> {
            ResponseEntity<byte[]> response = this.restTemplate.getForEntity("http://localhost:" + port + "/file/image/tiff/" + size, byte[].class);
            assertEquals(response.getStatusCode(), HttpStatus.OK);
        });
    }

    @Test
    public void testFileIcoSize() throws Exception {
        ICOSIZES.stream().forEach(size -> {
            ResponseEntity<byte[]> response = this.restTemplate.getForEntity("http://localhost:" + port + "/file/image/ico/" + size, byte[].class);
            assertEquals(response.getStatusCode(), HttpStatus.OK);
        });
    }

}
