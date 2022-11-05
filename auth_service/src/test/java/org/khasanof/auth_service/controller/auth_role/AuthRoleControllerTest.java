package org.khasanof.auth_service.controller.auth_role;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khasanof.auth_service.autoMock.AutoMockMvc;
import org.khasanof.auth_service.autoMock.Utils;
import org.khasanof.auth_service.dto.auth_role.AuthRoleCreateDTO;
import org.khasanof.auth_service.enums.auth_role.AuthRoleEnum;
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
public class AuthRoleControllerTest {

    @Autowired
    private AutoMockMvc autoMockMvc;

    @Test
    public void createMethodIsCreatedTest() throws Exception {
        var body = new AuthRoleCreateDTO("635bed067bd5df1c2f0e52b4", AuthRoleEnum.USER);
        autoMockMvc.obsessivePost("/api/v1/auth_role/create", body,
                Utils.matchers(Utils.StatusChoice.CREATED), MockMvcResultHandlers.print());
    }

    @Test
    public void createMethodIsBadRequestTest() throws Exception {
        var list = List.of(
                new AuthRoleCreateDTO("6320c3dbef1ded597035d89", AuthRoleEnum.USER),
                new AuthRoleCreateDTO("2167r3f", AuthRoleEnum.USER),
                new AuthRoleCreateDTO("1", AuthRoleEnum.USER),
                new AuthRoleCreateDTO("", AuthRoleEnum.USER),
                new AuthRoleCreateDTO("fuirehnfiu", AuthRoleEnum.USER),
                new AuthRoleCreateDTO("g4873yg", AuthRoleEnum.USER),
                new AuthRoleCreateDTO("2", AuthRoleEnum.USER)
        );

        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePost("/api/v1/auth_role/create", var,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void createMethodIsNotFoundTest() throws Exception {
        var body = new AuthRoleCreateDTO("635bed067bd5df1c2f0e52b4", AuthRoleEnum.USER);
        autoMockMvc.obsessivePost("/api/v1/auth_role/create", body,
                Utils.matchers(Utils.StatusChoice.NOT_ACCEPTABLE), MockMvcResultHandlers.print());
    }

    @Test
    public void deleteMethodIsNoContentTest() throws Exception {
        var var = "632d542418e04f1efb0cc8e2";
        autoMockMvc.obsessiveDelete("/api/v1/auth_role/delete/{id}", var,
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
                autoMockMvc.obsessiveDelete("/api/v1/auth_role/delete/{id}", var,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void deleteMethodIsNotFoundTest() throws Exception {
        var var = "63664e356dc3c65f00fb10ca";
        autoMockMvc.obsessiveDelete("/api/v1/auth_role/delete/{id}", var,
                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
    }

    @Test
    public void detailMethodIsOkTest() throws Exception {
        autoMockMvc.obsessiveGet("/api/v1/auth_role/detail/632d542418e04f1efb0cc8cf",
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }

    @Test
    public void detailMethodIsBadRequestTest() {
        List.of("1", "fydgytfdsfdsfds", "forbu")
                .forEach((elem) -> {
                    try {
                        autoMockMvc.obsessiveGet("/api/v1/auth_role/detail/{id}", elem,
                                Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Test
    public void detailMethodIsNotFoundTest() throws Exception {
        autoMockMvc.obsessiveGet("/api/v1/auth_role/detail/634975aae4d30c6c6b066de1",
                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());

    }

    @Test
    public void getMethodIsOkTest() throws Exception {
        autoMockMvc.obsessiveGet("/api/v1/auth_role/get/632d542418e04f1efb0cc8cf",
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }

    @Test
    public void getMethodIsBadRequestTest() {
        List.of("1", "fydgytfdsfdsfds", "forbu")
                .forEach((elem) -> {
                    try {
                        autoMockMvc.obsessiveGet("/api/v1/auth_role/get/{id}", elem,
                                Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Test
    public void getMethodIsNotFoundTest() throws Exception {
        autoMockMvc.obsessiveGet("/api/v1/auth_role/get/633568fb6f2f9218191858d1",
                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());

    }

    @Test
    public void listMethodIsOkFirstTest() throws Exception {
        LinkedMultiValueMap<String, String> map = Utils.MAP;
        map.add("fieldsEnum", "ROLE");
        autoMockMvc.obsessiveGet("/api/v1/auth_role/list", map,
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }


}
