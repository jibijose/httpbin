package com.jibi.controller;

import static org.hamcrest.core.Is.is;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/** The type Disk controller test. */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DiskControllerTest {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  /**
   * Test write.
   *
   * @throws Exception the exception
   */
  @Test
  void testWrite() throws Exception {
    ResponseEntity<String> response = null;

    response =
        this.restTemplate.getForEntity(
            "http://localhost:" + port + "/disk/write/MB/1", String.class);
    MatcherAssert.assertThat(response.getStatusCode(), is(HttpStatus.OK));

    response =
        this.restTemplate.getForEntity(
            "http://localhost:" + port + "/disk/write/GB/0", String.class);
    MatcherAssert.assertThat(response.getStatusCode(), is(HttpStatus.OK));

    response =
        this.restTemplate.getForEntity(
            "http://localhost:" + port + "/disk/write/TB/1", String.class);
    MatcherAssert.assertThat(response.getStatusCode(), is(HttpStatus.OK));
  }

  /**
   * Test read.
   *
   * @throws Exception the exception
   */
  @Test
  void testRead() throws Exception {
    ResponseEntity<String> response = null;

    response =
        this.restTemplate.getForEntity(
            "http://localhost:" + port + "/disk/read/MB/1", String.class);
    MatcherAssert.assertThat(response.getStatusCode(), is(HttpStatus.OK));

    response =
        this.restTemplate.getForEntity(
            "http://localhost:" + port + "/disk/read/GB/0", String.class);
    MatcherAssert.assertThat(response.getStatusCode(), is(HttpStatus.OK));

    response =
        this.restTemplate.getForEntity(
            "http://localhost:" + port + "/disk/read/TB/1", String.class);
    MatcherAssert.assertThat(response.getStatusCode(), is(HttpStatus.OK));
  }
}
