package org.khasanof.question_service.mapper.question;

import org.khasanof.question_service.dto.question.QuestionCreateDTO;
import org.khasanof.question_service.dto.question.QuestionDetailDTO;
import org.khasanof.question_service.dto.question.QuestionGetDTO;
import org.khasanof.question_service.dto.question.QuestionUpdateDTO;
import org.khasanof.question_service.entity.question.QuestionEntity;
import org.khasanof.question_service.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author Nurislom
 * Date: 12/6/2022
 * Time: 7:31 PM
 */
@Component
@Mapper(componentModel = "spring")
public interface QuestionMapper extends GenericMapper<QuestionCreateDTO, QuestionUpdateDTO, QuestionGetDTO, QuestionDetailDTO, QuestionEntity> {
}
