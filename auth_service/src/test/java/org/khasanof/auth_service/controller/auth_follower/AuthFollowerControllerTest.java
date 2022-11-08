package org.khasanof.auth_service.controller.auth_follower;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khasanof.auth_service.autoMock.AutoMockMvc;
import org.khasanof.auth_service.autoMock.Utils;
import org.khasanof.auth_service.dto.auth_follower.AuthFollowerCreateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class AuthFollowerControllerTest {

    @Autowired
    private AutoMockMvc autoMockMvc;


    @Test
    public void addMethodIsOkTest() throws Exception {
        var content = new AuthFollowerCreateDTO("6320c3daef1ded597035d899", List.of("6320c3daef1ded597035d899"));
        autoMockMvc.obsessivePost("/api/v1/auth_follower/add", content,
                Utils.matchers(Utils.StatusChoice.CREATED), MockMvcResultHandlers.print());
    }

    @Test
    public void addMethodBadRequestTest() throws Exception {
        var contentOne = new AuthFollowerCreateDTO("6320c3daef1ded597035d89", List.of("6320c3daef1ded597035d899"));
        var contentTwo = new AuthFollowerCreateDTO("6320c3daef1ded597035d899", List.of("6320c3daef1ded597035d89"));

        autoMockMvc.obsessivePost("/api/v1/auth_follower/add", contentOne,
                Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());

        autoMockMvc.obsessivePost("/api/v1/auth_follower/add", contentTwo,
                Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
    }

    @Test
    public void addMethodNotFoundTest() throws Exception {
        var contentOne = new AuthFollowerCreateDTO("6320c3daef1ded597035d891", List.of("6320c3daef1ded597035d899"));
        var contentTwo = new AuthFollowerCreateDTO("6320c3daef1ded597035d899", List.of("6320c3daef1ded597035d891"));

        autoMockMvc.obsessivePost("/api/v1/auth_follower/add", contentOne,
                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());

        autoMockMvc.obsessivePost("/api/v1/auth_follower/add", contentTwo,
                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
    }

    @Test
    public void deleteMethodIsBadRequestTest() {
        List.of("1", "fydgytfdsfdsfds", "forbu")
                .forEach((elem) -> {
                    try {
                        autoMockMvc.obsessiveDelete("/api/v1/auth_follower/delete/" + elem,
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
                        autoMockMvc.obsessiveDelete("/api/v1/auth_follower/delete/{id}", elem,
                                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Test
    public void detailMethodIsNoContentTest() throws Exception {
        autoMockMvc.obsessiveGet("/api/v1/auth_follower/detail/633568fb6f2f9218191858d2",
                Utils.matchers(Utils.StatusChoice.NO_CONTENT), MockMvcResultHandlers.print());
    }

    @Test
    public void detailMethodIsBadRequestTest() {
        List.of("1", "fydgytfdsfdsfds", "forbu")
                .forEach((elem) -> {
                    try {
                        autoMockMvc.obsessiveGet("/api/v1/auth_follower/detail/{id}", elem,
                                Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Test
    public void detailMethodIsNotFoundTest() throws Exception {
        autoMockMvc.obsessiveGet("/api/v1/auth_follower/detail/633568fb6f2f9218191858d1",
                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());

    }

    @Test
    public void getMethodIsOkTest() throws Exception {
        autoMockMvc.obsessiveGet("/api/v1/auth_follower/get/633568fb6f2f9218191858d2",
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }

    @Test
    public void getMethodIsBadRequestTest() {
        List.of("1", "fydgytfdsfdsfds", "forbu")
                .forEach((elem) -> {
                    try {
                        autoMockMvc.obsessiveGet("/api/v1/auth_follower/get/{id}", elem,
                                Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Test
    public void getMethodIsNotFoundTest() throws Exception {
        autoMockMvc.obsessiveGet("/api/v1/auth_follower/get/633568fb6f2f9218191858d1",
                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());

    }

    @Test
    public void listMethodIsOkTest() throws Exception {
        autoMockMvc.obsessiveGet("/api/v1/auth_follower/list",
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }

}
