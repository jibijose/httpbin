package com.jibi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
  @ParameterizedTest
  @CsvSource({"millis,10", "seconds,1", "minutes,0", "unknown,1"})
  void testDelayConstant(String unit, int time) {
    ResponseEntity<Void> response =
        this.restTemplate.getForEntity(
            "http://localhost:" + port + "/delay/" + unit + "/" + time, Void.class);
    assertEquals(response.getStatusCode(), HttpStatus.OK);
  }

  /** Test delay random. */
  @ParameterizedTest
  @CsvSource({"millis,10", "seconds,1", "minutes,0", "unknown,1"})
  void testDelayRandom(String unit, int time) {
    ResponseEntity<Void> response =
        this.restTemplate.getForEntity(
            "http://localhost:" + port + "/delay/random/" + unit + "/" + time, Void.class);
    assertEquals(response.getStatusCode(), HttpStatus.OK);
  }

  /** Test delay random range. */
  @ParameterizedTest
  @CsvSource({"millis,10", "seconds,1", "minutes,0", "unknown,1"})
  void testDelayRandomRange(String unit, int maxTime) {
    ResponseEntity<Void> response =
        this.restTemplate.getForEntity(
            "http://localhost:" + port + "/delay/random/" + unit + "/range/0/" + maxTime,
            Void.class);
    assertEquals(response.getStatusCode(), HttpStatus.OK);
  }
}
