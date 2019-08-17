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
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.anyOf;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StatusCodesControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test200StatusCode() throws Exception {
        ResponseEntity<Void> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/status/200", Void.class);
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
    }

    @Test
    public void test400StatusCode() throws Exception {
        ResponseEntity<Void> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/status/400", Void.class);
        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
    }

    @Test
    public void test500StatusCode() throws Exception {
        ResponseEntity<Void> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/status/500", Void.class);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseEntity.getStatusCodeValue());
    }

    @Test
    public void testIncorrectStatusCode() throws Exception {
        ResponseEntity<Void> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/status/999", Void.class);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseEntity.getStatusCodeValue());
    }

    @Test
    public void test200400500StatusRandomCode() throws Exception {
        ResponseEntity<Void> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/status/random/200,400,500", Void.class);
        org.hamcrest.MatcherAssert.assertThat(responseEntity.getStatusCodeValue(), anyOf(is(200), is(400), is(500)));
    }

    @Test
    public void test999StatusRandomCode() throws Exception {
        ResponseEntity<Void> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/status/random/999", Void.class);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseEntity.getStatusCodeValue());
    }
}
