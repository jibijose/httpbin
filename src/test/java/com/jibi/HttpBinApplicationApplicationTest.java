package com.jibi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HttpBinApplicationApplicationTest {

    @Test
    public void contextLoads() throws Exception {
    }

    @Test
    public void testMain() throws Exception {
        HttpBinApplication.main(new String[] {});
    }
}