package org.khasanof.question_service.validator.question_category;

import org.bson.types.ObjectId;
import org.khasanof.question_service.dto.question_category.QuestionCategoryCreateDTO;
import org.khasanof.question_service.dto.question_category.QuestionCategoryUpdateDTO;
import org.khasanof.question_service.exception.exceptions.InvalidValidationException;
import org.khasanof.question_service.validator.AbstractValidator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * @author Nurislom
 * Date: 12/7/2022
 * Time: 10:10 PM
 */
@Component
public class QuestionCategoryValidator extends AbstractValidator<QuestionCategoryCreateDTO, QuestionCategoryUpdateDTO, String> {

    @Override
    public void validCreateDTO(QuestionCategoryCreateDTO questionCategoryCreateDTO) throws InvalidValidationException {
        if (Objects.isNull(questionCategoryCreateDTO)) {
            throw new InvalidValidationException("DTO is null");
        }
        List<String> list = questionCategoryCreateDTO.getCategories();
        boolean anyMatch = list.stream()
                .anyMatch(any -> !ObjectId.isValid(any));
        if (anyMatch) {
            throw new InvalidValidationException("Invalid Category ID!");
        }
    }

    @Override
    public void validUpdateDTO(QuestionCategoryUpdateDTO questionCategoryUpdateDTO) throws InvalidValidationException {
        if (Objects.isNull(questionCategoryUpdateDTO)) {
            throw new InvalidValidationException("DTO is null");
        }
        if (!ObjectId.isValid(questionCategoryUpdateDTO.getId())) {
            throw new InvalidValidationException("Invalid ID!");
        }
        List<String> list = questionCategoryUpdateDTO.getCategories();
        boolean anyMatch = list.stream()
                .anyMatch(any -> !ObjectId.isValid(any));
        if (anyMatch) {
            throw new InvalidValidationException("Invalid Category ID!");
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
