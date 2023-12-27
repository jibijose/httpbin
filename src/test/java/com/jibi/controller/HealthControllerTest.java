package com.jibi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jibi.model.HealthModel;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;

/** The type Health controller test. */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HealthControllerTest {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  /**
   * Test json health.
   *
   * @throws Exception the exception
   */
  @Test
  void testJsonHealth() throws Exception {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<HealthModel> response =
        this.restTemplate.exchange(
            "http://localhost:" + port + "/health",
            HttpMethod.GET,
            requestEntity,
            HealthModel.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
    assertEquals("up", response.getBody().getStatus());
  }

  /**
   * Test xml health.
   *
   * @throws Exception the exception
   */
  @Test
  void testXmlHealth() throws Exception {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
    HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<HealthModel> response =
        this.restTemplate.exchange(
            "http://localhost:" + port + "/health",
            HttpMethod.GET,
            requestEntity,
            HealthModel.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(MediaType.APPLICATION_XML, response.getHeaders().getContentType());
    assertEquals("up", response.getBody().getStatus());
  }
}
