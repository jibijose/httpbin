package com.jibi.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.same;
import static org.powermock.api.mockito.PowerMockito.doThrow;
import static org.powermock.api.mockito.PowerMockito.spy;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DiskController.class)
@PowerMockIgnore({
  "com.sun.org.apache.xalan.*",
  "com.sun.org.apache.xerces.*",
  "javax.xml.*",
  "org.xml.*",
  "org.w3c.*"
})
public class DiskControllerPowerMockTest {

  private DiskController diskController;

  @Before
  public void setUp() {
    diskController = spy(new DiskController());
  }

  @Test(expected = RuntimeException.class)
  public void testWriteException() throws Exception {
    doThrow(new IOException()).when(diskController, "writeAndDeleteTempFile", any(byte[].class));

    diskController.write("MB", 1);
  }

  @Test(expected = RuntimeException.class)
  public void testReadException() throws Exception {
    doThrow(new IOException()).when(diskController, "getFileContent", same("1MB"));

    diskController.read("MB", 1);
  }
}
