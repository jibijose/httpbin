package com.jibi.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

/** The type Memory controller test. */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MemoryControllerTest {

  @LocalServerPort private int port;

  @Autowired private RestTemplate restTemplate;

  /**
   * Test memory hold.
   *
   * @throws Exception the exception
   */
  @Test
  void testMemoryHold() throws Exception {
    assertThat(
        this.restTemplate.getForObject(
            "http://localhost:" + port + "/memory/1024000/2", Void.class));
  }
}
