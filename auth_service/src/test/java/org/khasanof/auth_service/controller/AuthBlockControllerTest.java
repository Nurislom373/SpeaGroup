package org.khasanof.auth_service.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khasanof.auth_service.AutoMockMvcAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class AuthBlockControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AutoMockMvcAction mockMvcTest;

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    public void mockServer() {
        ClientHttpRequestFactory requestFactory = new MockMvcClientHttpRequestFactory(mockMvc);
        MockRestServiceServer serviceServer = MockRestServiceServer.bindTo(restTemplate).build();

        List.of(MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void detailMethodTest() throws Exception {
        mockMvcTest.obsessiveGet(mockMvc, "/api/v1/auth_block/detail/6338087b7cddaf25074e2185",
                matchers(), MockMvcResultHandlers.print());
    }

    private List<ResultMatcher> matchers() {
        return List.of(
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)
        );
    }

}
