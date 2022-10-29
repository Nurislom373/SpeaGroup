package org.khasanof.auth_service.controller.auth_block;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khasanof.auth_service.autoMock.AutoMockMvc;
import org.khasanof.auth_service.autoMock.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class AuthBlockControllerTest {

    @Autowired
    private AutoMockMvc mockMvcTest;

    @Test
    public void detailMethodTest() throws Exception {
        mockMvcTest.obsessiveGet("/api/v1/auth_block/detail/6338087b7cddaf25074e2185",
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }

    @Test
    public void getMethodTest() throws Exception {
        mockMvcTest.obsessiveGet("/api/v1/auth_block/get/6338087b7cddaf25074e2185",
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }

    @Test
    public void listMethodTest() throws Exception {
        mockMvcTest.obsessiveGet("/api/v1/auth_block/list", Utils.MAP,
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }


}
