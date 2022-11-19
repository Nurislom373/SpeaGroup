package org.khasanof.auth_service.controller.auth_user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khasanof.auth_service.dto.auth_user.AuthUserCreateDTO;
import org.khasanof.auth_service.enums.language.LanguageEnums;
import org.khasanof.auth_service.service.auth_user.AuthUserService;
import org.khasanof.auth_service.service.auth_user.AuthUserServiceImpl;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class AuthUserControllerMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthUserServiceImpl authUserService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createMethodTest() throws Exception {
        AuthUserCreateDTO dto = new AuthUserCreateDTO();
        dto.setPassword("123");
        dto.setUsername("rftyguh");
        dto.setDescription("fydsdfyuds fdyufgds ufdusfg dys fdusfdsugfdsuy fdysgfdsfdfsuydgsfdsuyfdyugsf ds");
        dto.setEmail("ftgyduhis@gmail.com");
        dto.setImagePath("ftydshfdgfgds.jpg");
        dto.setLastName("tygfhujd");
        dto.setFirstName("tfyguhij");
        dto.setLanguage(LanguageEnums.UZBEK);

        AuthUserService mockito = Mockito.mock(AuthUserServiceImpl.class);
        Mockito.doCallRealMethod().when(mockito).create(ArgumentMatchers.any(AuthUserCreateDTO.class));

        String dtoAsString = objectMapper.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth_user/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(dtoAsString).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", Matchers.equalTo("Successfully Created - User")))
                .andDo(MockMvcResultHandlers.print());
    }

}
