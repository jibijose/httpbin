package com.jibi.controller;

import com.jibi.model.SystemInfoModel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SystemInfoControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testSystemInfo() throws Exception {
        ResponseEntity<SystemInfoModel> response = this.restTemplate.getForEntity("http://localhost:" + port + "/system/info", SystemInfoModel.class);
        Assert.assertEquals("http code should be ok", HttpStatus.OK, response.getStatusCode());

        Assert.assertNotNull(response.getBody().getCpu());
        Assert.assertNotNull(response.getBody().getOs());
        Assert.assertNotNull(response.getBody().getMemory());
        Assert.assertNotNull(response.getBody().getDisks());
    }
}
