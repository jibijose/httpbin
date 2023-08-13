package com.jibi.controller;

import static org.hamcrest.core.Is.is;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/** The type Delay controller test. */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DelayControllerTest {
  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  /** Test delay constant. */
  @Test
  void testDelayConstant() {
    ResponseEntity<Void> response;

    response =
        this.restTemplate.getForEntity("http://localhost:" + port + "/delay/millis/10", Void.class);
    MatcherAssert.assertThat(response.getStatusCode(), is(HttpStatus.OK));

    response =
        this.restTemplate.getForEntity("http://localhost:" + port + "/delay/seconds/1", Void.class);
    MatcherAssert.assertThat(response.getStatusCode(), is(HttpStatus.OK));

    response =
        this.restTemplate.getForEntity("http://localhost:" + port + "/delay/minutes/0", Void.class);
    MatcherAssert.assertThat(response.getStatusCode(), is(HttpStatus.OK));

    response =
        this.restTemplate.getForEntity("http://localhost:" + port + "/delay/unknown/1", Void.class);
    MatcherAssert.assertThat(response.getStatusCode(), is(HttpStatus.OK));
  }

  /** Test delay random. */
  @Test
  void testDelayRandom() {
    ResponseEntity<Void> response;

    response =
        this.restTemplate.getForEntity(
            "http://localhost:" + port + "/delay/random/millis/10", Void.class);
    MatcherAssert.assertThat(response.getStatusCode(), is(HttpStatus.OK));

    response =
        this.restTemplate.getForEntity(
            "http://localhost:" + port + "/delay/random/seconds/1", Void.class);
    MatcherAssert.assertThat(response.getStatusCode(), is(HttpStatus.OK));

    response =
        this.restTemplate.getForEntity(
            "http://localhost:" + port + "/delay/random/minutes/0", Void.class);
    MatcherAssert.assertThat(response.getStatusCode(), is(HttpStatus.OK));

    response =
        this.restTemplate.getForEntity(
            "http://localhost:" + port + "/delay/random/unknown/1", Void.class);
    MatcherAssert.assertThat(response.getStatusCode(), is(HttpStatus.OK));
  }

  /** Test delay random range. */
  @Test
  void testDelayRandomRange() {
    ResponseEntity<Void> response;

    response =
        this.restTemplate.getForEntity(
            "http://localhost:" + port + "/delay/random/millis/range/0/10", Void.class);
    MatcherAssert.assertThat(response.getStatusCode(), is(HttpStatus.OK));

    response =
        this.restTemplate.getForEntity(
            "http://localhost:" + port + "/delay/random/seconds/range/0/1", Void.class);
    MatcherAssert.assertThat(response.getStatusCode(), is(HttpStatus.OK));

    response =
        this.restTemplate.getForEntity(
            "http://localhost:" + port + "/delay/random/minutes/range/0/0", Void.class);
    MatcherAssert.assertThat(response.getStatusCode(), is(HttpStatus.OK));

    response =
        this.restTemplate.getForEntity(
            "http://localhost:" + port + "/delay/random/unknown/range/0/1", Void.class);
    MatcherAssert.assertThat(response.getStatusCode(), is(HttpStatus.OK));
  }
}
