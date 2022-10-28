package org.khasanof.auth_service.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class AuthBlockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    public void mockServer() {
        ClientHttpRequestFactory requestFactory = new MockMvcClientHttpRequestFactory(mockMvc);
        MockRestServiceServer serviceServer = MockRestServiceServer.bindTo(restTemplate).build();
    }

    @Test
    public void detailMethodTest() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/auth_block/detail/{id}",
                                "632dac2cc6a9af3c205d7f1f"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print());
    }

}
