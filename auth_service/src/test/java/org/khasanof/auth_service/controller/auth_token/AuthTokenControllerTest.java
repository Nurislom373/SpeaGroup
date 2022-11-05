package org.khasanof.auth_service.controller.auth_token;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khasanof.auth_service.autoMock.AutoMockMvc;
import org.khasanof.auth_service.autoMock.Utils;
import org.khasanof.auth_service.dto.auth_token.AuthTokenCreateDTO;
import org.khasanof.auth_service.enums.auth_token.AuthTokenType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.util.LinkedMultiValueMap;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class AuthTokenControllerTest {

    @Autowired
    private AutoMockMvc autoMockMvc;

    @Test
    public void createMethodIsCreatedTest() throws Exception {
        var body1 = new AuthTokenCreateDTO("63517f0b066df716ed5c996c", AuthTokenType.ACCESS, "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqY29yaGFyZDAiLCJyb2xlIjoiQURNSU4iLCJleHAiOjE2Njc2NjkzNzgsImVtYWlsIjoiamNvcmhhcmQwQG5zdy5nb3YuYXUifQ.7Yx_IqMNaum1STGU2BWFrWuzwakqOjoEnyw5ilo8t0o", 50);
        var body2 = new AuthTokenCreateDTO("63517f0b066df716ed5c996c", AuthTokenType.REFRESH, "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqY29yaGFyZDAiLCJyb2xlIjoiQURNSU4iLCJleHAiOjE2Njc5MjU1NzgsImVtYWlsIjoiamNvcmhhcmQwQG5zdy5nb3YuYXUifQ._bdFCRTpqWs5DN5FB1LvlCRiZrV0YfSMUuoJUCEtWyw", 4360);

        autoMockMvc.obsessivePost("/api/v1/auth_token/create", body1,
                Utils.matchers(Utils.StatusChoice.CREATED), MockMvcResultHandlers.print());

        autoMockMvc.obsessivePost("/api/v1/auth_token/create", body2,
                Utils.matchers(Utils.StatusChoice.CREATED), MockMvcResultHandlers.print());
    }

    @Test
    public void createMethodIsBadRequestTest() throws Exception {
        var list = List.of(
                new AuthTokenCreateDTO("6320c3dbef1ded597035d89", AuthTokenType.ACCESS, "fuidsgdfu", 90),
                new AuthTokenCreateDTO("6320c3dbef1ded597035d89", AuthTokenType.ACCESS, "fuidsgdfu", 90),
                new AuthTokenCreateDTO("6320c3dbef1ded597035d89", AuthTokenType.ACCESS, "fuidsgdfu", 90),
                new AuthTokenCreateDTO("6320c3dbef1ded597035d89", AuthTokenType.ACCESS, "fuidsgdfu", 90),
                new AuthTokenCreateDTO("6320c3dbef1ded597035d89", AuthTokenType.ACCESS, "fuidsgdfu", 90),
                new AuthTokenCreateDTO("6320c3dbef1ded597035d89", AuthTokenType.ACCESS, "fuidsgdfu", -1)
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePost("/api/v1/auth_token/create", var,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void createMethodIsNotFoundTest() throws Exception {
        var body = new AuthTokenCreateDTO("635bed067bd5df1c2f0e52b1", AuthTokenType.REFRESH, "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqY29yaGFyZDAiLCJyb2xlIjoiQURNSU4iLCJleHAiOjE2Njc5MjU1NzgsImVtYWlsIjoiamNvcmhhcmQwQG5zdy5nb3YuYXUifQ._bdFCRTpqWs5DN5FB1LvlCRiZrV0YfSMUuoJUCEtWyw", 4360);
        autoMockMvc.obsessivePost("/api/v1/auth_token/create", body,
                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
    }

    @Test
    public void deleteMethodIsNoContentTest() throws Exception {
        var var = "63669698c9e1d27f091a1145";
        autoMockMvc.obsessiveDelete("/api/v1/auth_token/delete/{id}", var,
                Utils.matchers(Utils.StatusChoice.NO_CONTENT), MockMvcResultHandlers.print());
    }

    @Test
    public void deleteMethodIsBadRequestTest() throws Exception {
        var list = List.of(
                "1",
                "fshfshf",
                "6320c3daef1ded597035d89",
                "try43"
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessiveDelete("/api/v1/auth_token/delete/{id}", var,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void deleteMethodIsNotFoundTest() throws Exception {
        var var = "63669698c9e1d27f091a1145";
        autoMockMvc.obsessiveDelete("/api/v1/auth_token/delete/{id}", var,
                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
    }

    @Test
    public void detailMethodIsOkTest() throws Exception {
        autoMockMvc.obsessiveGet("/api/v1/auth_token/detail/63669698c9e1d27f091a1146",
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }

    @Test
    public void detailMethodIsBadRequestTest() {
        List.of("1", "fydgytfdsfdsfds", "forbu")
                .forEach((elem) -> {
                    try {
                        autoMockMvc.obsessiveGet("/api/v1/auth_token/detail/{id}", elem,
                                Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Test
    public void detailMethodIsNotFoundTest() throws Exception {
        autoMockMvc.obsessiveGet("/api/v1/auth_token/detail/634975aae4d30c6c6b066de1",
                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());

    }

    @Test
    public void getMethodIsOkTest() throws Exception {
        autoMockMvc.obsessiveGet("/api/v1/auth_token/get/63669698c9e1d27f091a1146",
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }

    @Test
    public void getMethodIsBadRequestTest() {
        List.of("1", "fydgytfdsfdsfds", "forbu")
                .forEach((elem) -> {
                    try {
                        autoMockMvc.obsessiveGet("/api/v1/auth_token/get/{id}", elem,
                                Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Test
    public void getMethodIsNotFoundTest() throws Exception {
        autoMockMvc.obsessiveGet("/api/v1/auth_token/get/633568fb6f2f9218191858d1",
                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());

    }

    @Test
    public void listMethodIsOkFirstTest() throws Exception {
        LinkedMultiValueMap<String, String> map = Utils.MAP;
        map.add("fieldsEnum", "TYPE");
        autoMockMvc.obsessiveGet("/api/v1/auth_token/list", map,
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }

    @Test
    public void listTypeMethodIsOkFirstTest() throws Exception {
        LinkedMultiValueMap<String, String> map = Utils.MAP;
        map.add("type", "ACCESS");
        autoMockMvc.obsessiveGet("/api/v1/auth_token/listType", map,
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }


}
