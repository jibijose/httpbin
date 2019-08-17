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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StatusCodesControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test200StatusCodes() throws Exception {
        ResponseEntity<Void> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/status/200", Void.class);
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
    }

    @Test
    public void test400StatusCodes() throws Exception {
        ResponseEntity<Void> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/status/400", Void.class);
        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
    }

    @Test
    public void test500StatusCodes() throws Exception {
        ResponseEntity<Void> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/status/500", Void.class);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseEntity.getStatusCodeValue());
    }

    @Test
    public void testIncorrectStatusCode() throws Exception {
        ResponseEntity<Void> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/status/999", Void.class);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseEntity.getStatusCodeValue());
    }
}
