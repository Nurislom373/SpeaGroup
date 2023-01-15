package org.khasanof.question_service.service.question_report;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khasanof.question_service.dto.question_report.QuestionReportCreateDTO;
import org.khasanof.question_service.dto.question_report.QuestionReportGetDTO;
import org.khasanof.question_service.entity.question.QuestionEntity;
import org.khasanof.question_service.entity.question_report.QuestionReportEntity;
import org.khasanof.question_service.entity.report.ReportEntity;
import org.khasanof.question_service.enums.report.ReportsEnum;
import org.khasanof.question_service.exception.exceptions.InvalidValidationException;
import org.khasanof.question_service.mapper.question_report.QuestionReportMapper;
import org.khasanof.question_service.repository.question_report.QuestionReportRepository;
import org.khasanof.question_service.service.question.QuestionService;
import org.khasanof.question_service.validator.question_report.QuestionReportValidator;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

/**
 * Author: Nurislom
 * <br/>
 * Date: 1/12/2023
 * <br/>
 * Time: 10:31 PM
 * <br/>
 * Package: org.khasanof.question_service.service.question_report
 */
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class QuestionReportServiceTest {

    private QuestionReportService service;

    @Mock
    private QuestionReportRepository repository;

    @Qualifier("questionReportMapperImpl")
    @Autowired
    private QuestionReportMapper mapper;

    @Autowired
    private QuestionReportValidator validator;

    @Autowired
    private QuestionService questionService;

    @BeforeEach
    void setUp() {
        service = new QuestionReportServiceImpl(repository, mapper, validator, questionService);
    }

    @Test
    public void serviceIsNotNull() {
        Assertions.assertThat(service).isNotNull();
    }

    @Test
    void createMethodNoExceptionTest() {
        var dto = new QuestionReportCreateDTO("639222e7310fa325a86e7758", ReportsEnum.COPYRIGHT,
                "639222e7310fa325a86e7758", "wfdsj fdhsygdf  fdgsuyf  yufdsguy fds");

        var mock = Mockito.mock(QuestionReportServiceImpl.class);
        Mockito.doNothing().when(mock).create(ArgumentMatchers.any());
        mock.create(dto);

        Mockito.verify(mock, Mockito.times(1)).create(dto);
    }

    @Test
    void createMethodInvalidValidationExceptionTest() {
        org.junit.jupiter.api.Assertions.assertThrows(InvalidValidationException.class,
                () -> {
                    service.create(null);
                });
    }


    @Test
    void getMethodNoExceptionTest() {
        var question = new QuestionEntity("6320c3dbef1ded597035d89a", "What String and StringBuilder?",
                null, false);

        var list = List.of(
                new ReportEntity("6320c3dbef1ded597035d89a", ReportsEnum.BULLYING, "Why is StringBuilder?"),
                new ReportEntity("6320c3dbef1ded597035d891", ReportsEnum.HARMFUL_ACTIVITIES, "fdugfydsgdfds")
        );

        var entity = new QuestionReportEntity(question, list);

        Mockito.when(repository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(entity));
        QuestionReportGetDTO dto = service.get("639336bf8d8e4760352648db");

        org.junit.jupiter.api.Assertions.assertEquals(2, dto.getReportsCount());
        org.junit.jupiter.api.Assertions.assertEquals(55, dto.getTotalPoint());
    }

}
