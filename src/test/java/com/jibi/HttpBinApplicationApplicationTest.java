package com.jibi;

import net.jcip.annotations.ThreadSafe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ThreadSafe
public class HttpBinApplicationApplicationTest {

    @Test
    public void contextLoads() throws Exception {
    }

    @Test
    public void testMain() throws Exception {
        HttpBinApplication.main(new String[] {});
    }
}
