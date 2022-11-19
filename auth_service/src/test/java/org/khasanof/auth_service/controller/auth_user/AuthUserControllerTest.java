package org.khasanof.auth_service.controller.auth_user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khasanof.auth_service.autoMock.AutoMockMvc;
import org.khasanof.auth_service.autoMock.Utils;
import org.khasanof.auth_service.dto.auth_user.AuthUserUpdateDTO;
import org.khasanof.auth_service.enums.language.LanguageEnums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class AuthUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AutoMockMvc autoMockMvc;

    @Test
    public void updateMethodIsOkTest() throws Exception {
        AuthUserUpdateDTO dto = new AuthUserUpdateDTO("635920993c40830fe14b0cc0","Joeann", "Corhard", "jcorhard0@nsw.gov.au",
                "Hypertensive retinopathy, unspecified eye", "jcorhard0", LanguageEnums.ENGLISH);

        autoMockMvc.obsessivePut("/api/v1/auth_user/update", dto,
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }

    @Test
    public void updateMethodIsBadRequestTest() {
        var list = List.of(
                new AuthUserUpdateDTO("635920993c40830fe14b0cc0","Jo", "Corhard", "jcorhard0@nsw.gov.au",
                "Hypertensive retinopathy, unspecified eye", "jcorhard0", LanguageEnums.ENGLISH),
                new AuthUserUpdateDTO("635920993c40830fe14b0cc0","Joeann", "Co", "jcorhard0@nsw.gov.au",
                "Hypertensive retinopathy, unspecified eye", "jcorhard0", LanguageEnums.ENGLISH),
                new AuthUserUpdateDTO("635920993c40830fe14b0cc0","Joeann", "Corhard", "jcor",
                        "Hypertensive retinopathy, unspecified eye", "jcorhard0", LanguageEnums.ENGLISH),
                new AuthUserUpdateDTO("635920993c40830fe14b0cc0","Joeann", "Corhard", "jcorhard0@nsw.gov.au",
                        "Hypertensive retinopathy, unspecified eye", "jc", LanguageEnums.ENGLISH));
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePut("/api/v1/auth_user/update", var,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void updateMethodIsNotFoundTest() {
        var list = List.of(new AuthUserUpdateDTO("635920993c40830fe14b0cd0","Jovuvfguvgdu", "Corhard", "jcorhard0@nsw.gov.au",
                        "Hypertensive retinopathy, unspecified eye", "jcorhard0", LanguageEnums.ENGLISH),
                new AuthUserUpdateDTO("635920993c40830fe14b0cd1","Joeann", "Cfdfdgdgfdgo", "jcorhard0@nsw.gov.au",
                        "Hypertensive retinopathy, unspecified eye", "jcorhard0", LanguageEnums.ENGLISH),
                new AuthUserUpdateDTO("635920993c40830fe14b0cd2","Joeann", "Corhard", "jcorhard0@nsw.gov.au",
                        "Hypertensive retinopathy, unspecified eye", "jcorhard0", LanguageEnums.ENGLISH),
                new AuthUserUpdateDTO("635920993c40830fe14b0cd3","Joeann", "Corhard", "jcorhard0@nsw.gov.au",
                        "Hypertensive retinopathy, unspecified eye", "jcgfdgfdgf", LanguageEnums.ENGLISH));
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePut("/api/v1/auth_user/update", var,
                        Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void deleteMethodIsNoContentTest() throws Exception {
        autoMockMvc.obsessiveDelete("/api/v1/auth_user/delete/632dac2cc6a9af3c205d7f1f",
                Utils.matchers(Utils.StatusChoice.NO_CONTENT), MockMvcResultHandlers.print());
    }

    @Test
    public void deleteMethodIsBadRequestTest() {
        var list = List.of("fyegwui", "fdsiufgs", " ", "yufgdsyu");
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessiveDelete("/api/v1/auth_user/delete/{id}", var,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void deleteMethodIsNotFoundTest() {
        var list = List.of("6320c3daef1ded597035d898", "6320c3daef1ded597035d897", "6320c3daef1ded597035d896");
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessiveDelete("/api/v1/auth_user/delete/{id}", var,
                        Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void getMethodTest() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/auth_user/get/{id}",
                                "632dac2cc6a9af3c205d7f1f"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print());

        MvcResult mvcResult2 = mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/auth_user/get/{id}",
                                "632dac2cc6a9af3c205d7f1"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult2.getResponse().getStatus());
    }

    @Test
    public void getMethodIsBadRequestTest() {
        var list = List.of("fyegwui", "fdsiufgs", " ", "yufgdsyu");
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessiveGet("/api/v1/auth_user/get/{id}", var,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void getMethodIsNotFoundTest() {
        var list = List.of("6320c3daef1ded597035d898", "6320c3daef1ded597035d897", "6320c3daef1ded597035d896");
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessiveGet("/api/v1/auth_user/get/{id}", var,
                        Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void detailMethodTest() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/auth_user/detail/{id}",
                                "632dac2cc6a9af3c205d7f1f"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void detailMethodIsBadRequestTest() {
        var list = List.of("fyegwui", "fdsiufgs", " ", "yufgdsyu");
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessiveGet("/api/v1/auth_user/detail/{id}", var,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void detailMethodIsNotFoundTest() {
        var list = List.of("6320c3daef1ded597035d898", "6320c3daef1ded597035d897", "6320c3daef1ded597035d896");
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessiveGet("/api/v1/auth_user/detail/{id}", var,
                        Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void listMethodTest() throws Exception {
        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("size", "20");
        map.add("page", "0");
        map.add("direction", "ASC");
        map.add("fieldsEnum", "USERNAME");

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/auth_user/list")
                .params(map)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[*].id").isNotEmpty());
    }

}
