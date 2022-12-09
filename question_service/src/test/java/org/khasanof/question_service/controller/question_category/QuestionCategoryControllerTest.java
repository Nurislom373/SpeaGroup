package org.khasanof.question_service.controller.question_category;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khasanof.question_service.autoMock.AutoMockMvc;
import org.khasanof.question_service.autoMock.Utils;
import org.khasanof.question_service.dto.question_category.QuestionCategoryAddDTO;
import org.khasanof.question_service.dto.question_category.QuestionCategoryCreateDTO;
import org.khasanof.question_service.dto.question_category.QuestionCategoryDeleteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/8/2022
 * <br/>
 * Time: 10:31 PM
 * <br/>
 * Package: org.khasanof.question_service.controller.question_category
 */
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class QuestionCategoryControllerTest {

    @Autowired
    private AutoMockMvc autoMockMvc;

    @Test
    public void createMethodIsCreatedResponseTest() throws Exception {
        var content = new QuestionCategoryCreateDTO("63922650e0e74f1c865f7e2b",
                List.of("63932cc0f7a6db5bddc03ffe", "63932cd5f7a6db5bddc03fff"));
        autoMockMvc.obsessivePost("/api/v1/question_category/create", content,
                Utils.matchers(Utils.StatusChoice.CREATED), MockMvcResultHandlers.print());
    }

    @Test
    public void createMethodIsBadRequestResponseTest() {
        var list = List.of(
                new QuestionCategoryCreateDTO("63922650e0e74f1c865f7e2b",
                        null),

                new QuestionCategoryCreateDTO("63922650e0e74f1c865f7e2",
                        List.of("63932cc0f7a6db5bddc03ffe", "63932cd5f7a6db5bddc03fff")),

                new QuestionCategoryCreateDTO("63922650e0e74f1c865f7e2b",
                        List.of("63932cc0f7a6db5bddc03ff", "63932cd5f7a6db5bddc03fff")),

                new QuestionCategoryCreateDTO("63922650e0e74f1c865f7e2b",
                        List.of("63932cc0f7a6db5bddc03ffe", "63932cd5f7a6db5bddc03ff"))
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePost("/api/v1/question_category/create", var,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void createMethodIsNotFoundResponseTest() {
        var list = List.of(
                new QuestionCategoryCreateDTO("63922650e0e74f1c865f7e21",
                        List.of("63932cc0f7a6db5bddc03ffe", "63932cd5f7a6db5bddc03fff")),

                new QuestionCategoryCreateDTO("63922650e0e74f1c865f7e22",
                        List.of("63932cc0f7a6db5bddc03ffe", "63932cd5f7a6db5bddc03fff")),

                new QuestionCategoryCreateDTO("63922650e0e74f1c865f7e23",
                        List.of("63932cc0f7a6db5bddc03ffe", "63932cd5f7a6db5bddc03fff")),

                new QuestionCategoryCreateDTO("63922650e0e74f1c865f7e24",
                        List.of("63932cc0f7a6db5bddc03ffe", "63932cd5f7a6db5bddc03fff"))
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePost("/api/v1/question_category/create", var,
                        Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void addCategoryMethodIsCreatedResponseTest() throws Exception {
        var content = new QuestionCategoryAddDTO("639336bf8d8e4760352648db",
                List.of("63934410f4fadc2a1e019052"));
        autoMockMvc.obsessivePut("/api/v1/question_category/addCategory", content,
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }

    @Test
    public void addCategoryMethodIsBadRequestResponseTest() {
        var list = List.of(
                new QuestionCategoryAddDTO("63922650e0e74f1c865f7e2b",
                        null),

                new QuestionCategoryAddDTO("63922650e0e74f1c865f7e",
                        List.of("63932cc0f7a6db5bddc03ffe", "63932cd5f7a6db5bddc03fff")),

                new QuestionCategoryAddDTO("63922650e0e74f1c865f7e2b",
                        List.of("63932cc0f7a6db5bddc03ff", "63932cd5f7a6db5bddc03fff")),

                new QuestionCategoryAddDTO("63922650e0e74f1c865f7e2b",
                        List.of("63932cc0f7a6db5bddc03ffe", "63932cd5f7a6db5bddc03ff"))
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePut("/api/v1/question_category/addCategory", var,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void addCategoryMethodIsNotFoundResponseTest() {
        var list = List.of(
                new QuestionCategoryAddDTO("63922650e0e74f1c865f7e21",
                        List.of("63932cc0f7a6db5bddc03ffe", "63932cd5f7a6db5bddc03fff")),

                new QuestionCategoryAddDTO("63922650e0e74f1c865f7e22",
                        List.of("63932cc0f7a6db5bddc03ffe", "63932cd5f7a6db5bddc03fff")),

                new QuestionCategoryAddDTO("63922650e0e74f1c865f7e23",
                        List.of("63932cc0f7a6db5bddc03ffe", "63932cd5f7a6db5bddc03fff")),

                new QuestionCategoryAddDTO("63922650e0e74f1c865f7e24",
                        List.of("63932cc0f7a6db5bddc03ffe", "63932cd5f7a6db5bddc03fff"))
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePut("/api/v1/question_category/addCategory", var,
                        Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void deleteCategoryMethodIsCreatedResponseTest() throws Exception {
        var content = new QuestionCategoryDeleteDTO("639336bf8d8e4760352648db",
                List.of("63934410f4fadc2a1e019052"));
        autoMockMvc.obsessivePut("/api/v1/question_category/deleteCategory", content,
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }

    @Test
    public void deleteCategoryMethodIsBadRequestResponseTest() {
        var list = List.of(
                new QuestionCategoryDeleteDTO("63922650e0e74f1c865f7e2b",
                        null),

                new QuestionCategoryDeleteDTO("63922650e0e74f1c865f7e",
                        List.of("63932cc0f7a6db5bddc03ffe", "63932cd5f7a6db5bddc03fff")),

                new QuestionCategoryDeleteDTO("63922650e0e74f1c865f7e2b",
                        List.of("63932cc0f7a6db5bddc03ff", "63932cd5f7a6db5bddc03fff")),

                new QuestionCategoryDeleteDTO("63922650e0e74f1c865f7e2b",
                        List.of("63932cc0f7a6db5bddc03ffe", "63932cd5f7a6db5bddc03ff"))
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePut("/api/v1/question_category/deleteCategory", var,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void deleteCategoryMethodIsNotFoundResponseTest() {
        var list = List.of(
                new QuestionCategoryDeleteDTO("63922650e0e74f1c865f7e21",
                        List.of("63932cc0f7a6db5bddc03ffe", "63932cd5f7a6db5bddc03fff")),

                new QuestionCategoryDeleteDTO("63922650e0e74f1c865f7e22",
                        List.of("63932cc0f7a6db5bddc03ffe", "63932cd5f7a6db5bddc03fff")),

                new QuestionCategoryDeleteDTO("63922650e0e74f1c865f7e23",
                        List.of("63932cc0f7a6db5bddc03ffe", "63932cd5f7a6db5bddc03fff")),

                new QuestionCategoryDeleteDTO("63922650e0e74f1c865f7e24",
                        List.of("63932cc0f7a6db5bddc03ffe", "63932cd5f7a6db5bddc03fff"))
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePut("/api/v1/question_category/deleteCategory", var,
                        Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void getMethodIsOkResponseTest() throws Exception {
        var content = "639336bf8d8e4760352648db";
        autoMockMvc.obsessiveGet("/api/v1/question_category/get/{id}", content,
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }

    @Test
    public void getMethodIsBadRequestResponseTest() {
        var list = List.of(
                "639336bf8d8e4760352648d",
                "21890864732",
                "fiudhfudsfgds",
                "fm89mu38y3g",
                " "
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessiveGet("/api/v1/question_category/get/{id}", var,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void getMethodIsNotFoundResponseTest() {
        var list = List.of(
                "639336bf8d8e4760352648d1",
                "639336bf8d8e4760352648d2",
                "639336bf8d8e4760352648d3",
                "639336bf8d8e4760352648d4",
                "639336bf8d8e4760352648d5"
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessiveGet("/api/v1/question_category/get/{id}", var,
                        Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void detailMethodIsOkResponseTest() throws Exception {
        var content = "639336bf8d8e4760352648db";
        autoMockMvc.obsessiveGet("/api/v1/question_category/detail/{id}", content,
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }

    @Test
    public void detailMethodIsBadRequestResponseTest() {
        var list = List.of(
                "639336bf8d8e4760352648d",
                "21890864732",
                "fiudhfudsfgds",
                "fm89mu38y3g",
                " "
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessiveGet("/api/v1/question_category/detail/{id}", var,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void detailMethodIsNotFoundResponseTest() {
        var list = List.of(
                "639336bf8d8e4760352648d1",
                "639336bf8d8e4760352648d2",
                "639336bf8d8e4760352648d3",
                "639336bf8d8e4760352648d4",
                "639336bf8d8e4760352648d5"
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessiveGet("/api/v1/question_category/detail/{id}", var,
                        Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void listMethodIsOkResponseTest() throws Exception {
        autoMockMvc.obsessiveGet("/api/v1/question_category/list",
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }


}
