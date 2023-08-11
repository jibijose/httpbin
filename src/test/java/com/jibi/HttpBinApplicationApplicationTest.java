package com.jibi;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

/** The type Http bin application application test. */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HttpBinApplicationApplicationTest {

  /**
   * Context loads.
   *
   * @throws Exception the exception
   */
  @Test
  void contextLoads(ApplicationContext context) throws Exception {
    assertNotNull(context);
  }

  /**
   * Test main.
   *
   * @throws Exception the exception
   */
  @Test
  void testMain(ApplicationContext context) throws Exception {
    HttpBinApplication.main(new String[] {});
    assertNotNull(context);
  }
}
