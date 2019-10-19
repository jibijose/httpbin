package com.jibi.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FilControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testFileJpg() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/file/jpg", byte[].class));
    }

    @Test
    public void testFileGif() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/file/gif", byte[].class));
    }

    @Test
    public void testFilePng() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/file/png", byte[].class));
    }

    @Test
    public void testFileTiff() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/file/tiff", byte[].class));
    }

    @Test
    public void testFileIco() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/file/ico", byte[].class));
    }
}
