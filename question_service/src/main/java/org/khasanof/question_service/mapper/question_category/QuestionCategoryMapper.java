package org.khasanof.question_service.mapper.question_category;

import org.khasanof.question_service.dto.question_category.QuestionCategoryCreateDTO;
import org.khasanof.question_service.dto.question_category.QuestionCategoryDetailDTO;
import org.khasanof.question_service.dto.question_category.QuestionCategoryGetDTO;
import org.khasanof.question_service.dto.question_category.QuestionCategoryUpdateDTO;
import org.khasanof.question_service.entity.question_category.QuestionCategoryEntity;
import org.khasanof.question_service.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author Nurislom
 * Date: 12/7/2022
 * Time: 10:09 PM
 */
@Component
@Mapper(componentModel = "spring")
public interface QuestionCategoryMapper extends GenericMapper<QuestionCategoryCreateDTO, QuestionCategoryUpdateDTO,
        QuestionCategoryGetDTO, QuestionCategoryDetailDTO, QuestionCategoryEntity> {
}
