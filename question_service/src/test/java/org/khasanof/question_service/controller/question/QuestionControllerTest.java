package org.khasanof.question_service.controller.question;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khasanof.question_service.autoMock.AutoMockMvc;
import org.khasanof.question_service.autoMock.Utils;
import org.khasanof.question_service.dto.question.QuestionCreateDTO;
import org.khasanof.question_service.dto.question.QuestionUpdateDTO;
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
 * Time: 10:42 PM
 * <br/>
 * Package: org.khasanof.question_service.controller.question
 */
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class QuestionControllerTest {

    @Autowired
    private AutoMockMvc autoMockMvc;

    @Test
    public void createMethodIsCreatedResponseTest() {
        var content = new QuestionCreateDTO("6320c3dbef1ded597035d89b",
                "Explain OOP Concept Encapsulation?", "python.png");
        try {
            autoMockMvc.obsessivePost("/api/v1/question/create", content,
                    Utils.matchers(Utils.StatusChoice.CREATED), MockMvcResultHandlers.print());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void createMethodIsBadRequestResponseTest() {
        var list = List.of(
                new QuestionCreateDTO("6320c3dbef1ded597035d89",
                        "Why is Java Simple Programming Language?", "java.png"),

                new QuestionCreateDTO("6320c3dbef1ded597035d89a",
                        "Why", "java.png"),

                new QuestionCreateDTO("6320c3dbef1ded597035d89a",
                        "Why is Java Simple Programming Language?", " ")
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePost("/api/v1/question/create", var,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void updateMethodIsOkResponseTest() {
        var content = new QuestionUpdateDTO("63922650e0e74f1c865f7e2b",
                "Explain OOP Concept Encapsulation?", "java.png");
        try {
            autoMockMvc.obsessivePut("/api/v1/question/update", content,
                    Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void updateMethodIsNotFoundResponseTest() {
        var list = List.of(
                new QuestionUpdateDTO("6320c3dbef1ded597035d89b",
                        "Why is Java Simple Programming Language?", "java.png"),

                new QuestionUpdateDTO("6320c3dbef1ded597035d89a",
                        "Why is Java Simple Programming Language?", "java.png"),

                new QuestionUpdateDTO("6320c3dbef1ded597035d89c",
                        "Why is Java Simple Programming Language?", "java.png")
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePut("/api/v1/question/update", var,
                        Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void updateMethodIsBadRequestResponseTest() {
        var list = List.of(
                new QuestionUpdateDTO("6320c3dbef1ded597035d89",
                        "Why is Java Simple Programming Language?", "java.png"),

                new QuestionUpdateDTO("6320c3dbef1ded597035d89a",
                        "Why", "java.png"),

                new QuestionUpdateDTO("6320c3dbef1ded597035d89c",
                        "Why is Java Simple Programming Language?", " ")
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePut("/api/v1/question/update", var,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void deleteMethodIsNoContentResponseTest() {
        var variable = "639222e7310fa325a86e7758";
        try {
            autoMockMvc.obsessiveDelete("/api/v1/question/delete/{id}", variable,
                    Utils.matchers(Utils.StatusChoice.NO_CONTENT), MockMvcResultHandlers.print());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void deleteMethodIsBadRequestResponseTest() {
        var list = List.of(
                "6320c3dbef1ded597035d89",
                "fudsdf98eeg",
                " ",
                "43875843",
                "1"
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessiveDelete("/api/v1/question/delete/{id}", var,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void deleteMethodIsNotFoundResponseTest() {
        var list = List.of(
                "639222e7310fa325a86e7757",
                "639222e7310fa325a86e7756",
                "639222e7310fa325a86e7755"
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessiveDelete("/api/v1/question/delete/{id}", var,
                        Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

}
