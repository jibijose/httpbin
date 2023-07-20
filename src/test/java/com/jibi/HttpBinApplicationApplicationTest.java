package com.jibi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpBinApplicationApplicationTest {

  @Test
  public void contextLoads() throws Exception {}

  @Test
  public void testMain() throws Exception {
    HttpBinApplication.main(new String[] {});
  }
}
