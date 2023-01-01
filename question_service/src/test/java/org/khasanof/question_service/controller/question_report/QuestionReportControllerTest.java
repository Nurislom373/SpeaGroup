package org.khasanof.question_service.controller.question_report;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khasanof.question_service.autoMock.AutoMockMvc;
import org.khasanof.question_service.autoMock.Utils;
import org.khasanof.question_service.dto.question_report.QuestionReportCreateDTO;
import org.khasanof.question_service.enums.report.ReportsEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

/**
 * Author: Nurislom
 * <br/>
 * Date: 1/1/2023
 * <br/>
 * Time: 9:37 PM
 * <br/>
 * Package: org.khasanof.question_service.controller.question_report
 */
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class QuestionReportControllerTest {

    @Autowired
    private AutoMockMvc autoMockMvc;

    @Test
    public void createMethodIsCreatedResponseTest() throws Exception {
        var dto = new QuestionReportCreateDTO("63922650e0e74f1c865f7e2b", ReportsEnum.OTHER,
                "63922650e0e74f1c865f7e2b", "fdgsfgdsygfdsfd");

        autoMockMvc.obsessivePost("/api/v1/question_report/create", dto,
                Utils.matchers(Utils.StatusChoice.CREATED), MockMvcResultHandlers.print());
    }

    @Test
    public void createMethodIsNotFoundResponseTest() {
        var dtos = List.of(
                new QuestionReportCreateDTO("63922650e0e74f1c865f7e21", ReportsEnum.OTHER,
                        "63922650e0e74f1c865f7e2b", "fdgsfgdsygfdsfd"),

                new QuestionReportCreateDTO("63922650e0e74f1c865f7e22", ReportsEnum.OTHER,
                        "63922650e0e74f1c865f7e2b", "fdgsfgdsygfdsfd"),

                new QuestionReportCreateDTO("63922650e0e74f1c865f7e23", ReportsEnum.OTHER,
                        "63922650e0e74f1c865f7e2b", "fdgsfgdsygfdsfd"),

                new QuestionReportCreateDTO("63922650e0e74f1c865f7e24", ReportsEnum.OTHER,
                        "63922650e0e74f1c865f7e2b", "fdgsfgdsygfdsfd")
        );
        dtos.forEach((dto) -> {
            try {
                autoMockMvc.obsessivePost("/api/v1/question_report/create", dto,
                        Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void createMethodIsBadRequestResponseTest() {
        var dtos = List.of(
                new QuestionReportCreateDTO("63922650e0e74f1c865f7e2", ReportsEnum.OTHER,
                        "63922650e0e74f1c865f7e2b", "fdgsfgdsygfdsfd"),

                new QuestionReportCreateDTO("63922650e0e74f1c865f7e22", ReportsEnum.OTHER,
                        "63922650e0e74f1c865f7e", "fdgsfgdsygfdsfd"),

                new QuestionReportCreateDTO("63922650e0e74f1c865f7e23", ReportsEnum.OTHER,
                        "63922650e0e74f1c865f7e2b", "fdgs")
        );
        dtos.forEach((dto) -> {
            try {
                autoMockMvc.obsessivePost("/api/v1/question_report/create", dto,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void deleteMethodIsNoContentResponseTest() throws Exception {
        var id = "63b1ba171055f80a9c34991f";
        autoMockMvc.obsessiveDelete("/api/v1/question_report/delete/{id}", id,
                Utils.matchers(Utils.StatusChoice.NO_CONTENT), MockMvcResultHandlers.print());
    }

    @Test
    public void deleteMethodIsNotFoundResponseTest() {
        var dtos = List.of(
                "63922650e0e74f1c865f7e2b",
                "63922650e0e74f1c865f7e21",
                "63922650e0e74f1c865f7e22",
                "63922650e0e74f1c865f7e24",
                "63922650e0e74f1c865f7e2d"
        );
        dtos.forEach((dto) -> {
            try {
                autoMockMvc.obsessiveDelete("/api/v1/question_report/delete/{id}", dto,
                        Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void deleteMethodIsBadRequestResponseTest() {
        var dtos = List.of(
                "1873527632",
                "fuidsgfuidgs",
                "fh3ho3yui43g",
                "fhoihdhfidugfd",
                " "
        );
        dtos.forEach((dto) -> {
            try {
                autoMockMvc.obsessiveDelete("/api/v1/question_report/delete/{id}", dto,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void getMethodIsOkResponseTest() throws Exception {
        var id = "63b1b8476dc21065a9a474f6";
        autoMockMvc.obsessiveGet("/api/v1/question_report/get/{id}", id,
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }

    @Test
    public void getMethodIsNotFoundResponseTest() {
        var dtos = List.of(
                "63922650e0e74f1c865f7e2b",
                "63922650e0e74f1c865f7e21",
                "63922650e0e74f1c865f7e22",
                "63922650e0e74f1c865f7e24",
                "63922650e0e74f1c865f7e2d"
        );
        dtos.forEach((dto) -> {
            try {
                autoMockMvc.obsessiveGet("/api/v1/question_report/get/{id}", dto,
                        Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void getMethodIsBadRequestResponseTest() {
        var dtos = List.of(
                "fdjsbfjds",
                "e3275e3872et732",
                "bdsjfdsjhfvdsh",
                "fdishfidusfuds",
                " "
        );
        dtos.forEach((dto) -> {
            try {
                autoMockMvc.obsessiveGet("/api/v1/question_report/get/{id}", dto,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void detailMethodIsOkResponseTest() throws Exception {
        var id = "63b1b8476dc21065a9a474f6";
        autoMockMvc.obsessiveGet("/api/v1/question_report/detail/{id}", id,
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }

    @Test
    public void detailMethodIsNotFoundResponseTest() {
        var dtos = List.of(
                "63922650e0e74f1c865f7e2b",
                "63922650e0e74f1c865f7e21",
                "63922650e0e74f1c865f7e22",
                "63922650e0e74f1c865f7e24",
                "63922650e0e74f1c865f7e2d"
        );
        dtos.forEach((dto) -> {
            try {
                autoMockMvc.obsessiveGet("/api/v1/question_report/detail/{id}", dto,
                        Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void detailMethodIsBadRequestResponseTest() {
        var dtos = List.of(
                "fdjsbfjds",
                "e3275e3872et732",
                "bdsjfdsjhfvdsh",
                "fdishfidusfuds",
                " "
        );
        dtos.forEach((dto) -> {
            try {
                autoMockMvc.obsessiveGet("/api/v1/question_report/detail/{id}", dto,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void listMethodIsOkResponseTest() throws Exception {
        autoMockMvc.obsessiveGet("/api/v1/question_report/list",
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }


}
