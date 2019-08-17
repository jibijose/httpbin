package com.jibi.controller;

import com.jibi.common.Util;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.doNothing;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringRunner.class)
@PrepareForTest(Util.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DelayControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void delay2Seconds() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/delay/seconds/2", Void.class));
    }

    @Test
    public void delay2Millis() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/delay/millis/10", Void.class));
    }

    @Test
    public void delay1Minutes() throws Exception {
        PowerMockito.spy(Util.class);
        doNothing().when(Util.class, "sleepSecondsSilent", anyInt());

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/delay/minutes/1", Void.class));
    }

    @Test
    public void delay1Wrong() throws Exception {
        ResponseEntity<Void> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/delay/wrong/1", Void.class);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseEntity.getStatusCodeValue());
    }
}
