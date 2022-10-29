package org.khasanof.auth_service.autoMock;

import org.khasanof.auth_service.autoMock.AutoMockMvc;
import org.khasanof.auth_service.autoMock.Utils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class AutoGDLControllerTest {

    private final String EXT;
    private final MockMvc mockMvc;
    private final AutoMockMvc autoMockMvc;

    public AutoGDLControllerTest(String ext, MockMvc mockMvc, AutoMockMvc autoMockMvc) {
        EXT = ext;
        this.mockMvc = mockMvc;
        this.autoMockMvc = autoMockMvc;
    }

    public void start() {

    }

    public void listMethod() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(Utils.PATH + EXT + Utils.LIST, Utils.MAP));
    }

    public void getMethod(String value) {

    }

}
