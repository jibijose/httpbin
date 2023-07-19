package com.jibi.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.Is.is;

import com.jibi.model.UploadInfoModel;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UploadDownloadControllerTest {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  @Test
  public void testUploadJson() throws Exception {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    headers.setContentType(MediaType.MULTIPART_FORM_DATA);

    MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
    body.add("file", new ClassPathResource("file/other/txt/10KB.txt"));

    HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

    ResponseEntity<UploadInfoModel> response =
        this.restTemplate.postForEntity(
            "http://localhost:" + port + "/upload", requestEntity, UploadInfoModel.class);
    Assert.assertEquals("http code should be ok", HttpStatus.OK, response.getStatusCode());
    Assert.assertEquals(
        "Content type should be json",
        MediaType.APPLICATION_JSON,
        response.getHeaders().getContentType());
    Assert.assertNotNull(response.getBody());
    Assert.assertEquals("Status should be success", "success", response.getBody().getStatus());
  }

  @Test
  public void testUploadXml() throws Exception {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
    headers.setContentType(MediaType.MULTIPART_FORM_DATA);

    MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
    body.add("file", new ClassPathResource("file/other/txt/10KB.txt"));

    HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

    ResponseEntity<UploadInfoModel> response =
        this.restTemplate.postForEntity(
            "http://localhost:" + port + "/upload", requestEntity, UploadInfoModel.class);
    Assert.assertEquals("http code should be ok", HttpStatus.OK, response.getStatusCode());
    Assert.assertEquals(
        "Content type should be xml",
        MediaType.APPLICATION_XML,
        response.getHeaders().getContentType());
    Assert.assertNotNull(response.getBody());
    Assert.assertEquals("Status should be success", "success", response.getBody().getStatus());
  }

  @Test
  public void testDownload() throws Exception {
    byte[] bytes =
        this.restTemplate.getForObject("http://localhost:" + port + "/download", byte[].class);
    assertThat(
        bytes.length,
        anyOf(
            is(1),
            is(10),
            is(100),
            is(1024),
            is(1024 * 10),
            is(1024 * 100),
            is(1024 * 1024),
            is(1024 * 1024 * 10)));
  }

  @Test
  public void testDownloadSize() throws Exception {
    byte[] bytes = null;
    bytes =
        this.restTemplate.getForObject("http://localhost:" + port + "/download/1B", byte[].class);
    assertThat(bytes.length, anyOf(is(1)));
    bytes =
        this.restTemplate.getForObject("http://localhost:" + port + "/download/10B", byte[].class);
    assertThat(bytes.length, anyOf(is(10)));
    bytes =
        this.restTemplate.getForObject("http://localhost:" + port + "/download/100B", byte[].class);
    assertThat(bytes.length, anyOf(is(100)));
    bytes =
        this.restTemplate.getForObject("http://localhost:" + port + "/download/1KB", byte[].class);
    assertThat(bytes.length, anyOf(is(1024)));
    bytes =
        this.restTemplate.getForObject("http://localhost:" + port + "/download/10KB", byte[].class);
    assertThat(bytes.length, anyOf(is(1024 * 10)));
    bytes =
        this.restTemplate.getForObject(
            "http://localhost:" + port + "/download/100KB", byte[].class);
    assertThat(bytes.length, anyOf(is(1024 * 100)));
    bytes =
        this.restTemplate.getForObject("http://localhost:" + port + "/download/1MB", byte[].class);
    assertThat(bytes.length, anyOf(is(1024 * 1024)));
    bytes =
        this.restTemplate.getForObject("http://localhost:" + port + "/download/10MB", byte[].class);
    assertThat(bytes.length, anyOf(is(1024 * 1024 * 10)));
  }
}
