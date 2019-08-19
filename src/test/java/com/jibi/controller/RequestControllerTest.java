package com.jibi.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RequestControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testRequestHeaders() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Header", "value");
        headers.set("Other-Header", "othervalue");
        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<Map> response = restTemplate.exchange("http://localhost:" + port + "/request/headers", HttpMethod.GET, entity, Map.class, new HashMap<String, String>());
        //assertThat(response.getBody().getClass(), Map.class));
    }
}
