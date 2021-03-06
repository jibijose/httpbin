package com.jibi.controller;

import com.jibi.model.HealthModel;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MultiValueMap;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HealthControllerTest {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  @Test
  public void testJsonHealth() throws Exception {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<HealthModel> response =
        this.restTemplate.exchange(
            "http://localhost:" + port + "/health",
            HttpMethod.GET,
            requestEntity,
            HealthModel.class);
    Assert.assertEquals("http code should be ok", HttpStatus.OK, response.getStatusCode());
    Assert.assertEquals(
        "Content type should be json",
        MediaType.APPLICATION_JSON,
        response.getHeaders().getContentType());
    Assert.assertEquals("Status should be up", "up", response.getBody().getStatus());
  }

  @Test
  public void testXmlHealth() throws Exception {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
    HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<HealthModel> response =
        this.restTemplate.exchange(
            "http://localhost:" + port + "/health",
            HttpMethod.GET,
            requestEntity,
            HealthModel.class);
    Assert.assertEquals("http code should be ok", HttpStatus.OK, response.getStatusCode());
    Assert.assertEquals(
        "Content type should be xml",
        MediaType.APPLICATION_XML,
        response.getHeaders().getContentType());
    Assert.assertEquals("Status should be up", "up", response.getBody().getStatus());
  }
}
