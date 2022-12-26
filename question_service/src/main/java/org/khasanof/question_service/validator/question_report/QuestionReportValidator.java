package org.khasanof.question_service.validator.question_report;

import org.bson.types.ObjectId;
import org.khasanof.question_service.dto.GenericDTO;
import org.khasanof.question_service.dto.question_report.QuestionReportCreateDTO;
import org.khasanof.question_service.exception.exceptions.InvalidValidationException;
import org.khasanof.question_service.validator.AbstractValidator;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/26/2022
 * <br/>
 * Time: 6:08 PM
 * <br/>
 * Package: org.khasanof.question_service.validator.question_report
 */
@Component
public class QuestionReportValidator extends AbstractValidator<QuestionReportCreateDTO, GenericDTO, String> {

    @Override
    public void validCreateDTO(QuestionReportCreateDTO questionReportCreateDTO) throws InvalidValidationException {
        if (Objects.isNull(questionReportCreateDTO)) {
            throw new InvalidValidationException("DTO is null");
        }
    }

    @Override
    public void validUpdateDTO(GenericDTO genericDTO) throws InvalidValidationException {

    }

    @Override
    public void validKey(String s) throws InvalidValidationException {
        if (Objects.isNull(s)) {
            throw new InvalidValidationException("ID is null");
        }
        if (!ObjectId.isValid(s)) {
            throw new InvalidValidationException("Invalid ID!");
        }
    }
}
