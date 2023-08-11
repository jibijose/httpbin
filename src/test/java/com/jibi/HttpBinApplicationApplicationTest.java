package com.jibi;

import org.junit.Assert;
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
    Assert.assertNotNull(context);
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
