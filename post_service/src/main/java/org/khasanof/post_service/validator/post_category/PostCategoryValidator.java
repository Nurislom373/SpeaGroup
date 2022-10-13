package org.khasanof.post_service.validator.post_category;

import org.khasanof.post_service.dto.GenericDTO;
import org.khasanof.post_service.dto.post_category.PostCategoryAddAllDTO;
import org.khasanof.post_service.dto.post_category.PostCategoryAddDTO;
import org.khasanof.post_service.exceptions.exceptions.InvalidValidationException;
import org.khasanof.post_service.validator.AbstractValidator;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PostCategoryValidator extends AbstractValidator<PostCategoryAddDTO, GenericDTO, String> {

    @Override
    public void validCreateDTO(PostCategoryAddDTO dto) throws InvalidValidationException {
        if (Objects.isNull(dto)) {
            throw new InvalidValidationException("DTO is null");
        }
    }

    public void validAddAllDTO(PostCategoryAddAllDTO dto) throws InvalidValidationException {
        if (Objects.isNull(dto)) {
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
    }
}
