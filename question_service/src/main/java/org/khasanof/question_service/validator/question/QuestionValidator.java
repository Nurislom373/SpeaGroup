package org.khasanof.question_service.validator.question;

import org.khasanof.post_service.exceptions.exceptions.InvalidValidationException;
import org.khasanof.question_service.dto.question.QuestionCreateDTO;
import org.khasanof.question_service.dto.question.QuestionGetDTO;
import org.khasanof.question_service.validator.AbstractValidator;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Nurislom
 * Date: 12/6/2022
 * Time: 7:33 PM
 */
@Component
public class QuestionValidator extends AbstractValidator<QuestionCreateDTO, QuestionGetDTO, String> {

    @Override
    public void validCreateDTO(QuestionCreateDTO questionCreateDTO) throws InvalidValidationException {
        if (Objects.isNull(questionCreateDTO)) {
            throw new InvalidValidationException("DTO is null");
        }
    }

    @Override
    public void validUpdateDTO(QuestionGetDTO questionGetDTO) throws InvalidValidationException {
        if (Objects.isNull(questionGetDTO)) {
            throw new InvalidValidationException("DTO is null");
        }
    }

    @Override
    public void validKey(String s) throws InvalidValidationException {
        if (Objects.isNull(s)) {
            throw new InvalidValidationException("ID is null");
        }
    }
}
