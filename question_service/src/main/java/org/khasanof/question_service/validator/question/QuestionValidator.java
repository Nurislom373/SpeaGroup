package org.khasanof.question_service.validator.question;

import org.bson.types.ObjectId;
import org.khasanof.question_service.dto.question.QuestionCreateDTO;
import org.khasanof.question_service.dto.question.QuestionUpdateDTO;
import org.khasanof.question_service.exception.exceptions.InvalidValidationException;
import org.khasanof.question_service.validator.AbstractValidator;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Nurislom
 * Date: 12/6/2022
 * Time: 7:33 PM
 */
@Component
public class QuestionValidator extends AbstractValidator<QuestionCreateDTO, QuestionUpdateDTO, String> {

    @Override
    public void validCreateDTO(QuestionCreateDTO questionCreateDTO) throws InvalidValidationException {
        if (Objects.isNull(questionCreateDTO)) {
            throw new InvalidValidationException("DTO is null");
        }
    }

    @Override
    public void validUpdateDTO(QuestionUpdateDTO dto) throws InvalidValidationException {
        if (Objects.isNull(dto)) {
            throw new InvalidValidationException("DTO is null");
        }
        if (!ObjectId.isValid(dto.getId())) {
            throw new InvalidValidationException("Invalid ID!");
        }
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
