package com.jibi.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.jibi.model.UploadInfoModel;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/** The type Upload download controller test. */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UploadDownloadControllerTest {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  /**
   * Test upload json.
   *
   * @throws Exception the exception
   */
  @Test
  void testUploadJson() throws Exception {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    headers.setContentType(MediaType.MULTIPART_FORM_DATA);

    MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
    body.add("file", new ClassPathResource("file/other/txt/10KB.txt"));

    HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

    ResponseEntity<UploadInfoModel> response =
        this.restTemplate.postForEntity(
            "http://localhost:" + port + "/upload", requestEntity, UploadInfoModel.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
    assertNotNull(response.getBody());
    assertEquals("success", response.getBody().getStatus());
  }

  /**
   * Test upload xml.
   *
   * @throws Exception the exception
   */
  @Test
  void testUploadXml() throws Exception {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
    headers.setContentType(MediaType.MULTIPART_FORM_DATA);

    MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
    body.add("file", new ClassPathResource("file/other/txt/10KB.txt"));

    HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

    ResponseEntity<UploadInfoModel> response =
        this.restTemplate.postForEntity(
            "http://localhost:" + port + "/upload", requestEntity, UploadInfoModel.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(MediaType.APPLICATION_XML, response.getHeaders().getContentType());
    assertNotNull(response.getBody());
    assertEquals("success", response.getBody().getStatus());
  }

  /**
   * Test download.
   *
   * @throws Exception the exception
   */
  @Test
  void testDownload() throws Exception {
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

  /**
   * Test download size.
   *
   * @throws Exception the exception
   */
  @Test
  void testDownloadSize() throws Exception {
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
