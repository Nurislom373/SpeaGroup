package org.khasanof.auth_service.validator.category;

import org.khasanof.auth_service.dto.category.CategoryCreateDTO;
import org.khasanof.auth_service.dto.category.CategoryUpdateDTO;
import org.khasanof.auth_service.exception.exceptions.InvalidValidationException;
import org.khasanof.auth_service.validator.AbstractValidator;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CategoryValidator extends AbstractValidator<CategoryCreateDTO, CategoryUpdateDTO, String> {
    @Override
    public void validCreateDTO(CategoryCreateDTO categoryCreateDTO) throws InvalidValidationException {
        if (Objects.isNull(categoryCreateDTO)) {
            throw new InvalidValidationException("DTO is null");
        }
    }

    @Override
    public void validUpdateDTO(CategoryUpdateDTO categoryUpdateDTO) throws InvalidValidationException {
        if (Objects.isNull(categoryUpdateDTO)) {
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
