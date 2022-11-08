package org.khasanof.auth_service.controller.auth_following;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khasanof.auth_service.autoMock.AutoMockMvc;
import org.khasanof.auth_service.autoMock.Utils;
import org.khasanof.auth_service.dto.auth_following.AuthFollowingCreateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class AuthFollowingControllerTest {

    @Autowired
    private AutoMockMvc autoMockMvc;

    @Test
    public void addMethodIsOkTest() throws Exception {
        var content = new AuthFollowingCreateDTO("6320c3dbef1ded597035d89a", List.of("6320c3dbef1ded597035d89a"));
        autoMockMvc.obsessivePost("/api/v1/auth_following/add", content,
                Utils.matchers(Utils.StatusChoice.CREATED), MockMvcResultHandlers.print());
    }

    @Test
    public void addMethodBadRequestTest() throws Exception {
        var contentOne = new AuthFollowingCreateDTO("6320c3daef1ded597035d89", List.of("6320c3daef1ded597035d899"));
        var contentTwo = new AuthFollowingCreateDTO("6320c3daef1ded597035d899", List.of("6320c3daef1ded597035d89"));

        autoMockMvc.obsessivePost("/api/v1/auth_following/add", contentOne,
                Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());

        autoMockMvc.obsessivePost("/api/v1/auth_following/add", contentTwo,
                Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
    }

    @Test
    public void addMethodNotFoundTest() throws Exception {
        var contentOne = new AuthFollowingCreateDTO("6320c3daef1ded597035d891", List.of("6320c3daef1ded597035d899"));
        var contentTwo = new AuthFollowingCreateDTO("6320c3daef1ded597035d899", List.of("6320c3daef1ded597035d891"));

        autoMockMvc.obsessivePost("/api/v1/auth_following/add", contentOne,
                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());

        autoMockMvc.obsessivePost("/api/v1/auth_following/add", contentTwo,
                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
    }

    @Test
    public void deleteMethodIsBadRequestTest() {
        List.of("1", "fydgytfdsfdsfds", "forbu")
                .forEach((elem) -> {
                    try {
                        autoMockMvc.obsessiveDelete("/api/v1/auth_following/delete/" + elem,
                                Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Test
    public void deleteMethodIsNotFoundTest() {
        List.of("633568fb6f2f9218191858d1", "633568fb6f2f9218191858d3", "633568fb6f2f9218191858d4")
                .forEach((elem) -> {
                    try {
                        autoMockMvc.obsessiveDelete("/api/v1/auth_following/delete/{id}", elem,
                                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Test
    public void detailMethodIsNoContentTest() throws Exception {
        autoMockMvc.obsessiveGet("/api/v1/auth_following/detail/634975aae4d30c6c6b066de5",
                Utils.matchers(Utils.StatusChoice.NO_CONTENT), MockMvcResultHandlers.print());
    }

    @Test
    public void detailMethodIsBadRequestTest() {
        List.of("1", "fydgytfdsfdsfds", "forbu")
                .forEach((elem) -> {
                    try {
                        autoMockMvc.obsessiveGet("/api/v1/auth_following/detail/{id}", elem,
                                Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Test
    public void detailMethodIsNotFoundTest() throws Exception {
        autoMockMvc.obsessiveGet("/api/v1/auth_following/detail/634975aae4d30c6c6b066de1",
                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());

    }

    @Test
    public void getMethodIsOkTest() throws Exception {
        autoMockMvc.obsessiveGet("/api/v1/auth_following/get/634975aae4d30c6c6b066de5",
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }

    @Test
    public void getMethodIsBadRequestTest() {
        List.of("1", "fydgytfdsfdsfds", "forbu")
                .forEach((elem) -> {
                    try {
                        autoMockMvc.obsessiveGet("/api/v1/auth_following/get/{id}", elem,
                                Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Test
    public void getMethodIsNotFoundTest() throws Exception {
        autoMockMvc.obsessiveGet("/api/v1/auth_following/get/633568fb6f2f9218191858d1",
                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());

    }

    @Test
    public void listMethodIsOkTest() throws Exception {
        autoMockMvc.obsessiveGet("/api/v1/auth_following/list",
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }


}
