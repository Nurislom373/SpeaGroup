package org.khasanof.auth_service.controller.auth_block;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khasanof.auth_service.autoMock.AutoMockMvc;
import org.khasanof.auth_service.autoMock.Utils;
import org.khasanof.auth_service.dto.auth_block.AuthBlockCreateDTO;
import org.khasanof.auth_service.service.auth_block.AuthBlockServiceImpl;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class AuthBlockControllerMockTest {

    @MockBean
    private AuthBlockServiceImpl blockService;

    @Autowired
    private AutoMockMvc mockMvcData;

    @Test
    public void create() throws Exception {
        var dto = new AuthBlockCreateDTO("6320c3dbef1ded597035d8a7", "633568fb6f2f9218191858d5");
        var mockito = Mockito.mock(AuthBlockServiceImpl.class);
        Mockito.doCallRealMethod().when(mockito).create(ArgumentMatchers.any());
        mockMvcData.obsessivePost(Utils.PATH + Utils.ControllerExt.AUTH_BLOCK.getValue() + Utils.CREATE, dto,
                Utils.matchers(Utils.StatusChoice.CREATED), MockMvcResultHandlers.print());
    }

    @Test
    public void delete() throws Exception {
        var id = "/6338087b7cddaf25074e2185";
        var mockito = Mockito.mock(AuthBlockServiceImpl.class);
        Mockito.doCallRealMethod().when(mockito).delete(ArgumentMatchers.any());
        mockMvcData.obsessiveDelete(Utils.PATH + Utils.ControllerExt.AUTH_BLOCK.getValue() + Utils.DELETE + id,
                Utils.matchers(Utils.StatusChoice.NO_CONTENT), MockMvcResultHandlers.print());

    }

}
