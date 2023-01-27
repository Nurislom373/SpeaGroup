package org.khasanof.question_service.service.question_report;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khasanof.question_service.criteria.question_category.QuestionCategoryCriteria;
import org.khasanof.question_service.criteria.question_report.QuestionReportCriteria;
import org.khasanof.question_service.dto.question_category.QuestionCategoryGetDTO;
import org.khasanof.question_service.dto.question_report.QuestionReportCreateDTO;
import org.khasanof.question_service.dto.question_report.QuestionReportDetailDTO;
import org.khasanof.question_service.dto.question_report.QuestionReportGetDTO;
import org.khasanof.question_service.entity.question.QuestionEntity;
import org.khasanof.question_service.entity.question_report.QuestionReportEntity;
import org.khasanof.question_service.entity.report.ReportEntity;
import org.khasanof.question_service.enums.report.ReportsEnum;
import org.khasanof.question_service.exception.exceptions.InvalidValidationException;
import org.khasanof.question_service.exception.exceptions.NotFoundException;
import org.khasanof.question_service.mapper.question_report.QuestionReportMapper;
import org.khasanof.question_service.repository.question_report.QuestionReportRepository;
import org.khasanof.question_service.service.question.QuestionService;
import org.khasanof.question_service.service.question_category.QuestionCategoryServiceImpl;
import org.khasanof.question_service.validator.question_report.QuestionReportValidator;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.ArrayList;
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

    @Test
    void getMethodNotFoundException() {
        Mockito.when(repository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());

        org.junit.jupiter.api.Assertions.assertThrows(NotFoundException.class,
                () -> service.get("639336bf8d8e4760352648db"));
    }

    @Test
    void getMethodInvalidValidationException() {
        org.junit.jupiter.api.Assertions.assertThrows(InvalidValidationException.class,
                () -> service.get("639336bf8d8e4760352648d"));
    }

    @Test
    void detailMethodNoExceptionTest() {
        var question = new QuestionEntity("639336bf8d8e4760352648db", "sfuhdsduifgdsfdsfds",
                "fdishfuidgsufd.png", false);

        List<ReportEntity> list = List.of(
                new ReportEntity("639336bf8d8e4760352648db", ReportsEnum.COPYRIGHT, "Every thing will be ok."),
                new ReportEntity("639336bf8d8e4760352648db", ReportsEnum.OTHER, "Wake up early."),
                new ReportEntity("639336bf8d8e4760352648db", ReportsEnum.HATE_SPEECH, "Never give up.")
        );

        var entity = new QuestionReportEntity(question, list);

        Mockito.when(repository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(entity));
        var detail = service.detail("639336bf8d8e4760352648db");

        Assertions.assertThat(detail).isNotNull();
        org.junit.jupiter.api.Assertions.assertEquals(entity.getReports().size(), detail.getCount());
    }

    @Test
    void detailMethodNotFoundException() {
        Mockito.when(repository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());

        org.junit.jupiter.api.Assertions.assertThrows(NotFoundException.class,
                () -> service.detail("639336bf8d8e4760352648db"));
    }

    @Test
    void detailMethodInvalidValidationException() {
        org.junit.jupiter.api.Assertions.assertThrows(InvalidValidationException.class,
                () -> service.detail("639336bf8d8e4760352648d"));
    }

    @Test
    void deleteMethodNoExceptionTest() {
        var mock = Mockito.mock(QuestionReportServiceImpl.class);
        Mockito.doNothing().when(mock).delete(ArgumentMatchers.any());

        mock.delete("639336bf8d8e4760352648db");
        Mockito.verify(mock, Mockito.times(1)).delete("639336bf8d8e4760352648db");
    }

    @Test
    public void deleteMethodNotFoundExceptionTest() {
        Mockito.when(repository.existsById(ArgumentMatchers.any())).thenReturn(false);

        org.junit.jupiter.api.Assertions.assertThrows(NotFoundException.class,
                () -> service.delete("639336bf8d8e4760352648db"));
    }

    @Test
    public void deleteMethodInvalidValidationExceptionTest() {
        org.junit.jupiter.api.Assertions.assertThrows(InvalidValidationException.class,
                () -> service.delete("639336bf8d8e4760352648d"));
    }

    @Test
    public void listMethodTest() {
        var list = List.of(
                new QuestionReportGetDTO("639336bf8d8e4760352648db", 5, 90, Instant.now()),
                new QuestionReportGetDTO("639336bf8d8e4760352648db", 3, 735, Instant.now()),
                new QuestionReportGetDTO("639336bf8d8e4760352648db", 8, 432, Instant.now()),
                new QuestionReportGetDTO("639336bf8d8e4760352648db", 9, 4372, Instant.now())
        );

        var mock = Mockito.mock(QuestionReportServiceImpl.class);
        Mockito.when(mock.list(ArgumentMatchers.any())).thenReturn(list);
        var dtos = mock.list(new QuestionReportCriteria());

        Assertions.assertThat(dtos).isNotNull();
        org.junit.jupiter.api.Assertions.assertEquals(list.size(), dtos.size());
    }



}
