package com.jibi.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

/**
 * The type Cpu controller test.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CpuControllerTest {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

    /**
     * Test cpu all processors.
     *
     * @throws Exception the exception
     */
    @Test
  public void testCpuAllProcessors() throws Exception {
    assertThat(
        this.restTemplate.getForObject("http://localhost:" + port + "/cpu/all/20/2", Void.class));
  }

    /**
     * Test cpu single processor.
     *
     * @throws Exception the exception
     */
    @Test
  public void testCpuSingleProcessor() throws Exception {
    assertThat(
        this.restTemplate.getForObject(
            "http://localhost:" + port + "/cpu/single/20/2", Void.class));
  }
}
