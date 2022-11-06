package org.khasanof.auth_service.controller.blocked_for;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khasanof.auth_service.autoMock.AutoMockMvc;
import org.khasanof.auth_service.autoMock.Utils;
import org.khasanof.auth_service.dto.blocked_for.BlockedForCreateDTO;
import org.khasanof.auth_service.dto.blocked_for.BlockedForUpdateDTO;
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
public class BlockedForControllerTest {

    @Autowired
    private AutoMockMvc autoMockMvc;

    @Test
    public void createMethodIsCreatedTest() throws Exception {
        var body = new BlockedForCreateDTO("Created", "CREATED", 10);
        autoMockMvc.obsessivePost("/api/v1/blocked_for/create", body,
                Utils.matchers(Utils.StatusChoice.CREATED), MockMvcResultHandlers.print());
    }

    @Test
    public void createMethodIsBadRequestTest() throws Exception {
        var list = List.of(
                new BlockedForCreateDTO("12", "fuidsgdfu", 90),
                new BlockedForCreateDTO("t5", "fuidsgdfu", 90),
                new BlockedForCreateDTO("6320c3dbef1ded597035d89", "2", 90),
                new BlockedForCreateDTO("6320c3dbef1ded597035d89", "", 90),
                new BlockedForCreateDTO("6320c3dbef1ded597035d89", "fuidsgdfu", -1)
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePost("/api/v1/blocked_for/create", var,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void createMethodIsNotAcceptableTest() throws Exception {
        var body1 = new BlockedForCreateDTO("63517f0b066df716ed5c996c", "CREATED", 50);

        autoMockMvc.obsessivePost("/api/v1/blocked_for/create", body1,
                Utils.matchers(Utils.StatusChoice.NOT_ACCEPTABLE), MockMvcResultHandlers.print());
    }

    @Test
    public void updateMethodIsOkTest() throws Exception {
        var list = List.of(
                new BlockedForUpdateDTO("6367a1ab3245a13c11a0e12c", "63628e0f6eebe000c3dc798b", "2006", 2));
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePut("/api/v1/blocked_for/update", var,
                        Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void updateMethodIsBadRequestTest() throws Exception {
        var list = List.of(
                new BlockedForUpdateDTO("6367a1ab3245a13c11a0e12", "63628e0f6eebe000c3dc798", "2006", 2),
                new BlockedForUpdateDTO("6367a1ab3245a13c11a0e12c","12", "2006", 1),
                new BlockedForUpdateDTO("6367a1ab3245a13c11a0e12c","12321dsf", "20", 3),
                new BlockedForUpdateDTO("6367a1ab3245a13c11a0e12c","12", "2006", -1)
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePut("/api/v1/blocked_for/update", var,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void updateMethodIsNotFoundTest() throws Exception {
        var list = List.of(
                new BlockedForUpdateDTO("6367a1ab3245a13c11a0e122", "2006", "+9985764653", 2),
                new BlockedForUpdateDTO("6367a1ab3245a13c11a0e121", "2006", "+9985764653", 2)
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePut("/api/v1/blocked_for/update", var,
                        Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }


    @Test
    public void deleteMethodIsNoContentTest() throws Exception {
        var var = "6367a1ab3245a13c11a0e12c";
        autoMockMvc.obsessiveDelete("/api/v1/blocked_for/delete/{id}", var,
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
                autoMockMvc.obsessiveDelete("/api/v1/blocked_for/delete/{id}", var,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void deleteMethodIsNotFoundTest() throws Exception {
        var var = "63669698c9e1d27f091a1145";
        autoMockMvc.obsessiveDelete("/api/v1/blocked_for/delete/{id}", var,
                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
    }

    @Test
    public void getMethodIsOkTest() throws Exception {
        autoMockMvc.obsessiveGet("/api/v1/blocked_for/get/633568fb6f2f9218191858d3",
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }

    @Test
    public void getMethodIsBadRequestTest() {
        List.of("1", "fydgytfdsfdsfds", "forbu")
                .forEach((elem) -> {
                    try {
                        autoMockMvc.obsessiveGet("/api/v1/blocked_for/get/{id}", elem,
                                Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Test
    public void getMethodIsNotFoundTest() throws Exception {
        autoMockMvc.obsessiveGet("/api/v1/blocked_for/get/633568fb6f2f9218191858d1",
                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());

    }

    @Test
    public void listTypeMethodIsOkFirstTest() throws Exception {
        LinkedMultiValueMap<String, String> map = Utils.MAP;
        autoMockMvc.obsessiveGet("/api/v1/blocked_for/list", map,
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }


}
