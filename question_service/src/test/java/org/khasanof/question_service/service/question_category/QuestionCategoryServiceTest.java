package org.khasanof.question_service.service.question_category;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khasanof.question_service.dto.question_category.QuestionCategoryCreateDTO;
import org.khasanof.question_service.dto.question_category.QuestionCategoryGetDTO;
import org.khasanof.question_service.entity.question.QuestionEntity;
import org.khasanof.question_service.entity.question_category.QuestionCategoryEntity;
import org.khasanof.question_service.mapper.question_category.QuestionCategoryMapperImpl;
import org.khasanof.question_service.repository.question_category.QuestionCategoryRepository;
import org.khasanof.question_service.service.question.QuestionService;
import org.khasanof.question_service.validator.question_category.QuestionCategoryValidator;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/9/2022
 * <br/>
 * Time: 8:29 PM
 * <br/>
 * Package: org.khasanof.question_service.service.question_category
 */
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class QuestionCategoryServiceTest {

    private QuestionCategoryService service;

    @Mock
    private QuestionCategoryRepository repository;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CategoryFeignClient feignClient;

    @BeforeEach
    void setUp() {
        service = new QuestionCategoryServiceImpl(repository, new QuestionCategoryMapperImpl(),
                new QuestionCategoryValidator(), questionService, feignClient);
    }

    @Test
    public void serviceIsNotNull() {
        Assertions.assertThat(service).isNotNull();
    }

    @Test
    public void createMethodTest() {
        var dto = new QuestionCategoryCreateDTO("639222e7310fa325a86e7758",
                List.of("63932cc0f7a6db5bddc03ffe", "63932cd5f7a6db5bddc03fff"));

        var mock = Mockito.mock(QuestionCategoryServiceImpl.class);
        Mockito.doNothing().when(mock).create(ArgumentMatchers.any());
        mock.create(dto);

        Mockito.verify(mock, Mockito.times(1)).create(dto);
    }

    @Test
    public void getMethodTest() {
        var entity = new QuestionCategoryEntity(new QuestionEntity("6320c3dbef1ded597035d89a", "fihsuigfduydsgfdsfs",
                "fdishfidsuhfds", false), List.of("63932cc0f7a6db5bddc03ffe", "63932cd5f7a6db5bddc03fff"));
        entity.setId("639336bf8d8e4760352648db");

        Mockito.when(repository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(entity));

        QuestionCategoryGetDTO dto = service.get("639336bf8d8e4760352648db");
        org.junit.jupiter.api.Assertions.assertEquals(entity.getQuestionId().getId(), dto.getQuestionIdVal());
    }
}
