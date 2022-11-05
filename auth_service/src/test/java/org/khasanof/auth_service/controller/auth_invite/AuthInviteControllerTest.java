package org.khasanof.auth_service.controller.auth_invite;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khasanof.auth_service.autoMock.AutoMockMvc;
import org.khasanof.auth_service.autoMock.Utils;
import org.khasanof.auth_service.dto.auth_invite.AuthInviteChangeStatusDTO;
import org.khasanof.auth_service.dto.auth_invite.AuthInviteCreateDTO;
import org.khasanof.auth_service.enums.auth_invite.AuthInviteStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class AuthInviteControllerTest {

    @Autowired
    private AutoMockMvc autoMockMvc;

    @Test
    public void createMethodIsCreatedTest() throws Exception {
        var body = new AuthInviteCreateDTO("6320c3dbef1ded597035d89a", "6320c3dbef1ded597035d8ac");
        autoMockMvc.obsessivePost("/api/v1/auth_invite/create", body,
                Utils.matchers(Utils.StatusChoice.CREATED), MockMvcResultHandlers.print());
    }

    @Test
    public void createMethodIsBadRequestTest() throws Exception {
        var list = List.of(
                new AuthInviteCreateDTO("6320c3dbef1ded597035d89a", "6320c3dbef1ded597035d89a"),
                new AuthInviteCreateDTO("6320c3dbef1ded597035d89b", "6320c3dbef1ded597035d8a0"),
                new AuthInviteCreateDTO("6320c3dbef1ded597035d89", "6320c3dbef1ded597035d8a0"),
                new AuthInviteCreateDTO("6320c3dbef1ded597035d89a", "uhfygfdgd"),
                new AuthInviteCreateDTO("6320c3dbef1ded597035d89a", "uhfygfdgd"),
                new AuthInviteCreateDTO("g4873yg", "1"),
                new AuthInviteCreateDTO("2", "gfdn")
        );

        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePost("/api/v1/auth_invite/create", var,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void createMethodIsNotFoundTest() throws Exception {
        var body = new AuthInviteCreateDTO("6320c3daef1ded597035d891", "6320c3dbef1ded597035d8a0");
        autoMockMvc.obsessivePost("/api/v1/auth_invite/create", body,
                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
    }

    @Test
    public void deleteMethodIsNoContentTest() throws Exception {
        var var = "63664e356dc3c65f00fb10ca";
        autoMockMvc.obsessiveDelete("/api/v1/auth_invite/delete/{id}", var,
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
                autoMockMvc.obsessiveDelete("/api/v1/auth_invite/delete/{id}", var,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void deleteMethodIsNotFoundTest() throws Exception {
        var var = "63664e356dc3c65f00fb10ca";
        autoMockMvc.obsessiveDelete("/api/v1/auth_invite/delete/{id}", var,
                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
    }

    @Test
    public void deleteInviteMethodIsNoContentTest() throws Exception {
        autoMockMvc.obsessiveDelete("/api/v1/auth_invite/deleteInvite/id=6363f8b78a0ffb5f1a39d471&inviteUserId=6320c3dbef1ded597035d8a0",
                Utils.matchers(Utils.StatusChoice.NO_CONTENT), MockMvcResultHandlers.print());
    }

    @Test
    public void deleteIviteMethodIsBadRequestTest() throws Exception {
        var list = new HashMap<String, String>();
        list.put("6363f8b78a0ffb5f1a39d471", "1");
        list.put("1", "6363f8b78a0ffb5f1a39d471");
        list.put("6363f8b78a0ffb5f1a39d47", "6363f8b78a0ffb5");
        list.put("freufbgrygf", "uegyf");
        list.put("fdjnfdbsd", "1");
        list.put("1273561", "1");
        list.forEach((var1, var2) -> {
            try {
                autoMockMvc.obsessiveDelete("/api/v1/auth_invite/deleteInvite/id=" + var1 + "&inviteUserId=" + var2,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void deleteInviteMethodIsNotFoundTest() throws Exception {
        autoMockMvc.obsessiveDelete("/api/v1/auth_invite/deleteInvite/id=6363f8b78a0ffb5f1a39d472&inviteUserId=6320c3dbef1ded597035d8a0",
                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
    }


    @Test
    public void inviteChangeStatusMethodIsNoContentTest() throws Exception {
        var var = new AuthInviteChangeStatusDTO("6363f8b78a0ffb5f1a39d471", "6320c3dbef1ded597035d8ac",
                AuthInviteStatusEnum.NO_ACCEPT);
        autoMockMvc.obsessivePut("/api/v1/auth_invite/inviteChangeStatus", var,
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }

    @Test
    public void inviteChangeStatusIsBadRequestTest() throws Exception {
        var list = List.of(
                new AuthInviteChangeStatusDTO("6363f8b78a0ffb5f1a39d47", "6320c3dbef1ded597035d8ac", AuthInviteStatusEnum.NO_ACCEPT),
                new AuthInviteChangeStatusDTO("6363f8b78a0ffb5f1a39d471", "6320c3dbef1ded597035d8a", AuthInviteStatusEnum.NO_ACCEPT),
                new AuthInviteChangeStatusDTO("1", "6320c3dbef1ded597035d8ac", AuthInviteStatusEnum.NO_ACCEPT),
                new AuthInviteChangeStatusDTO("6363f8b78a0ffb5f1a39d471", "1", AuthInviteStatusEnum.NO_ACCEPT)
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePut("/api/v1/auth_invite/inviteChangeStatus", var,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void inviteChangeStatusIsNotFoundTest() throws Exception {
        var list = List.of(
                new AuthInviteChangeStatusDTO("6363f8b78a0ffb5f1a39d472", "6320c3dbef1ded597035d8ac", AuthInviteStatusEnum.NO_ACCEPT),
                new AuthInviteChangeStatusDTO("6363f8b78a0ffb5f1a39d471", "6320c3dbef1ded597035d8a1", AuthInviteStatusEnum.NO_ACCEPT)
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePut("/api/v1/auth_invite/inviteChangeStatus", var,
                        Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void detailMethodIsOkTest() throws Exception {
        autoMockMvc.obsessiveGet("/api/v1/auth_invite/detail/6363f8b78a0ffb5f1a39d471",
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }

    @Test
    public void detailMethodIsBadRequestTest() {
        List.of("1", "fydgytfdsfdsfds", "forbu")
                .forEach((elem) -> {
                    try {
                        autoMockMvc.obsessiveGet("/api/v1/auth_invite/detail/{id}", elem,
                                Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Test
    public void detailMethodIsNotFoundTest() throws Exception {
        autoMockMvc.obsessiveGet("/api/v1/auth_invite/detail/634975aae4d30c6c6b066de1",
                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());

    }

    @Test
    public void getMethodIsOkTest() throws Exception {
        autoMockMvc.obsessiveGet("/api/v1/auth_invite/get/6363f8b78a0ffb5f1a39d471",
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }

    @Test
    public void getMethodIsBadRequestTest() {
        List.of("1", "fydgytfdsfdsfds", "forbu")
                .forEach((elem) -> {
                    try {
                        autoMockMvc.obsessiveGet("/api/v1/auth_invite/get/{id}", elem,
                                Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Test
    public void getMethodIsNotFoundTest() throws Exception {
        autoMockMvc.obsessiveGet("/api/v1/auth_invite/get/633568fb6f2f9218191858d1",
                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());

    }

    @Test
    public void listMethodIsOkTest() throws Exception {
        autoMockMvc.obsessiveGet("/api/v1/auth_invite/list",
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }







}
