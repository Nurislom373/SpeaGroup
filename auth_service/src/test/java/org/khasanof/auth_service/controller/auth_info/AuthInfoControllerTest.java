package org.khasanof.auth_service.controller.auth_info;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khasanof.auth_service.autoMock.AutoMockMvc;
import org.khasanof.auth_service.autoMock.Utils;
import org.khasanof.auth_service.dto.auth_info.AuthInfoCreateDTO;
import org.khasanof.auth_service.dto.auth_info.AuthInfoUpdateDTO;
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
public class AuthInfoControllerTest {

    @Autowired
    private AutoMockMvc autoMockMvc;

    @Test
    public void createMethodIsCreatedTest() throws Exception {
        var list = List.of(
                new AuthInfoCreateDTO("6320c3dbef1ded597035d89a", List.of("6325ee332088c03f152df33c", "6325ee332088c03f152df33d")),
                new AuthInfoCreateDTO("6320c3dbef1ded597035d89b", List.of("6325ee332088c03f152df33e", "6325ee332088c03f152df33f")),
                new AuthInfoCreateDTO("6320c3dbef1ded597035d89c", List.of("6325ee332088c03f152df340", "6325ee332088c03f152df341")));

        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePost("/api/v1/auth_info/create", var,
                        Utils.matchers(Utils.StatusChoice.CREATED), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void createMethodIsBadRequestTest() throws Exception {
        var list = List.of(
                new AuthInfoCreateDTO("6320c3dbef1ded597035d89", List.of("6325ee332088c03f152df33", "6325ee332088c03f152df33d")),
                new AuthInfoCreateDTO("2415321", List.of("6325ee332088c03f152df33e", "6325ee332088c03f152df33f")),
                new AuthInfoCreateDTO("$gyufdg", List.of("6325ee332088c03f152df340", "6325ee332088c03f152df341")));

        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePost("/api/v1/auth_info/create", var,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void createMethodIsNotAcceptableTest() throws Exception {
        var list = List.of(
                new AuthInfoCreateDTO("6320c3dbef1ded597035d89a", List.of("6325ee332088c03f152df33", "6325ee332088c03f152df33d")));
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePost("/api/v1/auth_info/create", var,
                        Utils.matchers(Utils.StatusChoice.NOT_ACCEPTABLE), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void createMethodIsNotFoundTest() throws Exception {
        var list = List.of(
                new AuthInfoCreateDTO("6320c3dbef1ded597035d891", List.of("6325ee332088c03f152df33", "6325ee332088c03f152df33d")));
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePost("/api/v1/auth_info/create", var,
                        Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void updateMethodIsOkTest() throws Exception {
        var list = List.of(
                new AuthInfoUpdateDTO("63628e0f6eebe000c3dc798b", "2006", "+9985764653"));
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePut("/api/v1/auth_info/update", var,
                        Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void updateMethodIsBadRequestTest() throws Exception {
        var list = List.of(
                new AuthInfoUpdateDTO("63628e0f6eebe000c3dc798", "2006", "+9985764653"),
                new AuthInfoUpdateDTO("563372", "2006", "+9985764653")
                );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePut("/api/v1/auth_info/update", var,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void updateMethodIsNotFoundTest() throws Exception {
        var list = List.of(
                new AuthInfoUpdateDTO("6361625ffb716e1c2b2dc201", "2006", "+9985764653"),
                new AuthInfoUpdateDTO("6361625ffb716e1c2b2dc203", "2006", "+9985764653")
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePut("/api/v1/auth_info/update", var,
                        Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }



    @Test
    public void detailMethodIsOkTest() throws Exception {
        autoMockMvc.obsessiveGet("/api/v1/auth_info/detail/6361625ffb716e1c2b2dc202",
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }

    @Test
    public void detailMethodIsBadRequestTest() {
        List.of("1", "fydgytfdsfdsfds", "forbu")
                .forEach((elem) -> {
                    try {
                        autoMockMvc.obsessiveGet("/api/v1/auth_info/detail/{id}", elem,
                                Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Test
    public void detailMethodIsNotFoundTest() throws Exception {
        autoMockMvc.obsessiveGet("/api/v1/auth_info/detail/634975aae4d30c6c6b066de1",
                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());

    }

    @Test
    public void getMethodIsOkTest() throws Exception {
        autoMockMvc.obsessiveGet("/api/v1/auth_info/get/6361625ffb716e1c2b2dc202",
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }

    @Test
    public void getMethodIsBadRequestTest() {
        List.of("1", "fydgytfdsfdsfds", "forbu")
                .forEach((elem) -> {
                    try {
                        autoMockMvc.obsessiveGet("/api/v1/auth_info/get/{id}", elem,
                                Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Test
    public void getMethodIsNotFoundTest() throws Exception {
        autoMockMvc.obsessiveGet("/api/v1/auth_info/get/633568fb6f2f9218191858d1",
                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());

    }

    @Test
    public void getCategoryListMethodIsOkTest() throws Exception {
        autoMockMvc.obsessiveGet("/api/v1/auth_info/getCategoryList/{id}", "6361625ffb716e1c2b2dc202",
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }

    @Test
    public void getCategoryListMethodIsBadRequestTest() throws Exception {
        List.of("1", "fydgytfdsfdsfds", "$fdusfds")
                .forEach((elem) -> {
                    try {
                        autoMockMvc.obsessiveGet("/api/v1/auth_info/getCategoryList/{id}", elem,
                                Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Test
    public void getCategoryListMethodIsNotFoundTest() throws Exception {
        List.of("6361625ffb716e1c2b2dc201", "6361625ffb716e1c2b2dc203", "6361625ffb716e1c2b2dc204")
                .forEach((elem) -> {
                    try {
                        autoMockMvc.obsessiveGet("/api/v1/auth_info/getCategoryList/{id}", elem,
                                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Test
    public void listMethodIsOkTest() throws Exception {
        LinkedMultiValueMap<String, String> map = Utils.MAP;
        map.add("fieldsEnum", "PHONE_NUMBER");
        autoMockMvc.obsessiveGet("/api/v1/auth_info/list", map,
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }


}
