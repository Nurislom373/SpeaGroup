package org.khasanof.question_service.validator.question_view;

import org.bson.types.ObjectId;
import org.khasanof.question_service.dto.GenericDTO;
import org.khasanof.question_service.dto.question_view.QuestionViewCreateDTO;
import org.khasanof.question_service.exception.exceptions.InvalidValidationException;
import org.khasanof.question_service.validator.AbstractValidator;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/26/2022
 * <br/>
 * Time: 8:38 PM
 * <br/>
 * Package: org.khasanof.question_service.validator.question_view
 */
@Component
public class QuestionViewValidator extends AbstractValidator<QuestionViewCreateDTO, GenericDTO, String> {

    @Override
    public void validCreateDTO(QuestionViewCreateDTO questionViewCreateDTO) throws InvalidValidationException {
        if (Objects.isNull(questionViewCreateDTO)) {
            throw new InvalidValidationException("DTO is null");
        }
    }

    @Override
    public void validUpdateDTO(GenericDTO genericDTO) throws InvalidValidationException {
        if (Objects.isNull(genericDTO)) {
            throw new InvalidValidationException("DTO is null");
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
