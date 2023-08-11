package com.jibi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/** The type Http bin application application test. */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HttpBinApplicationApplicationTest {

  /**
   * Context loads.
   *
   * @throws Exception the exception
   */
  @Test
  void contextLoads() throws Exception {
  }

  /**
   * Test main.
   *
   * @throws Exception the exception
   */
  @Test
  void testMain() throws Exception {
    HttpBinApplication.main(new String[] {});
  }
}
