package com.jibi.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RequestControllerTest {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  @Test
  public void testRequestHeadersJson() throws Exception {
    HttpHeaders headers = new HttpHeaders();
    headers.set("Accept", "application/json");
    HttpEntity entity = new HttpEntity(headers);

    ResponseEntity<Map> response =
        restTemplate.exchange(
            "http://localhost:" + port + "/request/headers",
            HttpMethod.GET,
            entity,
            Map.class,
            new HashMap<String, String>());
    assertEquals(response.getStatusCodeValue(), HttpStatus.OK.value());
    org.hamcrest.MatcherAssert.assertThat(
        response.getHeaders().getContentType().toString(),
        containsString(MediaType.APPLICATION_JSON_VALUE.toString()));
  }
}
