package org.khasanof.post_service.validator.category;

import org.bson.types.ObjectId;
import org.khasanof.post_service.dto.category.CategoryCreateDTO;
import org.khasanof.post_service.dto.category.CategoryUpdateDTO;
import org.khasanof.post_service.exceptions.exceptions.InvalidValidationException;
import org.khasanof.post_service.validator.AbstractValidator;
import org.springframework.stereotype.Component;

import java.util.List;
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
        if (!ObjectId.isValid(s)) {
            throw new InvalidValidationException("Invalid ID!");
        }
    }

    public void validKeys(List<String> ids) throws InvalidValidationException {
        Boolean orElse = ids
                .stream()
                .map(ObjectId::isValid)
                .filter(f -> !f)
                .findFirst().orElse(true);
        if (!orElse) {
            throw new InvalidValidationException("Invalid Id!");
        }
    }
}
