package com.jibi.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;

import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.doThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.same;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DiskController.class)
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