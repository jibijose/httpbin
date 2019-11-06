package com.jibi.controller;

import com.jibi.model.SystemInfoModel;
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

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SystemInfoControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testSystemInfoJson() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<SystemInfoModel> response = this.restTemplate.exchange("http://localhost:" + port + "/system/info", HttpMethod.GET, requestEntity, SystemInfoModel.class);
        Assert.assertEquals("http code should be ok", HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals("Content type should be json", MediaType.APPLICATION_JSON, response.getHeaders().getContentType());

        Assert.assertNotNull(response.getBody().getCpu());
        Assert.assertNotNull(response.getBody().getOs());
        Assert.assertNotNull(response.getBody().getMemory());
        Assert.assertNotNull(response.getBody().getDisks());
    }

    @Test
    public void testSystemInfoXml() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<SystemInfoModel> response = this.restTemplate.exchange("http://localhost:" + port + "/system/info", HttpMethod.GET, requestEntity, SystemInfoModel.class);
        Assert.assertEquals("http code should be ok", HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals("Content type should be xml", MediaType.APPLICATION_XML, response.getHeaders().getContentType());

        Assert.assertNotNull(response.getBody().getCpu());
        Assert.assertNotNull(response.getBody().getOs());
        Assert.assertNotNull(response.getBody().getMemory());
        Assert.assertNotNull(response.getBody().getDisks());
    }
}
