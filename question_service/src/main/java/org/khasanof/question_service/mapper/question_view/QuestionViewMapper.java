package org.khasanof.question_service.mapper.question_view;

import org.khasanof.question_service.dto.GenericDTO;
import org.khasanof.question_service.dto.question_view.QuestionViewCreateDTO;
import org.khasanof.question_service.dto.question_view.QuestionViewDetailDTO;
import org.khasanof.question_service.dto.question_view.QuestionViewGetDTO;
import org.khasanof.question_service.entity.question_view.QuestionViewEntity;
import org.khasanof.question_service.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/26/2022
 * <br/>
 * Time: 8:37 PM
 * <br/>
 * Package: org.khasanof.question_service.mapper.question_view
 */
@Component
@Mapper(componentModel = "spring")
public interface QuestionViewMapper extends GenericMapper<QuestionViewCreateDTO, GenericDTO, QuestionViewGetDTO, QuestionViewDetailDTO, QuestionViewEntity> {
}
