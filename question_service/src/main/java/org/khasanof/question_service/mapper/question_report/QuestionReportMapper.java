package org.khasanof.question_service.mapper.question_report;

import org.khasanof.question_service.dto.GenericDTO;
import org.khasanof.question_service.dto.question_report.QuestionReportCreateDTO;
import org.khasanof.question_service.dto.question_report.QuestionReportDetailDTO;
import org.khasanof.question_service.dto.question_report.QuestionReportGetDTO;
import org.khasanof.question_service.entity.question_report.QuestionReportEntity;
import org.khasanof.question_service.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/26/2022
 * <br/>
 * Time: 6:07 PM
 * <br/>
 * Package: org.khasanof.question_service.mapper.question_report
 */
@Component
@Mapper(componentModel = "spring")
public interface QuestionReportMapper extends GenericMapper<QuestionReportCreateDTO, GenericDTO,
        QuestionReportGetDTO, QuestionReportDetailDTO, QuestionReportEntity> {
}
