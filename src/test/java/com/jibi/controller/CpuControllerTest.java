package com.jibi.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CpuControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCpuAllProcessors() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/cpu/all/20/2", Void.class));
    }

    @Test
    public void testCpuSingleProcessor() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/cpu/single/20/2", Void.class));
    }
}
