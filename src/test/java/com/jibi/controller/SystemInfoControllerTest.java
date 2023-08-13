package com.jibi.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.jibi.model.SystemInfoModel;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;

/** The type System info controller test. */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SystemInfoControllerTest {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  /**
   * Test system info json.
   *
   * @throws Exception the exception
   */
  @Test
  void testSystemInfoJson() throws Exception {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<SystemInfoModel> response =
        this.restTemplate.exchange(
            "http://localhost:" + port + "/system/info",
            HttpMethod.GET,
            requestEntity,
            SystemInfoModel.class);
    Assert.assertEquals("http code should be ok", HttpStatus.OK, response.getStatusCode());
    Assert.assertEquals(
        "Content type should be json",
        MediaType.APPLICATION_JSON,
        response.getHeaders().getContentType());

    assertNotNull(response.getBody().getCpu());
    assertNotNull(response.getBody().getOs());
    assertNotNull(response.getBody().getMemory());
    assertNotNull(response.getBody().getDisks());
  }

  /**
   * Test system info xml.
   *
   * @throws Exception the exception
   */
  @Test
  void testSystemInfoXml() throws Exception {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
    HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<SystemInfoModel> response =
        this.restTemplate.exchange(
            "http://localhost:" + port + "/system/info",
            HttpMethod.GET,
            requestEntity,
            SystemInfoModel.class);
    Assert.assertEquals("http code should be ok", HttpStatus.OK, response.getStatusCode());
    Assert.assertEquals(
        "Content type should be xml",
        MediaType.APPLICATION_XML,
        response.getHeaders().getContentType());

    assertNotNull(response.getBody().getCpu());
    assertNotNull(response.getBody().getOs());
    assertNotNull(response.getBody().getMemory());
    assertNotNull(response.getBody().getDisks());
  }
}
