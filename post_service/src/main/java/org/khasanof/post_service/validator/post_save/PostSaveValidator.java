package org.khasanof.post_service.validator.post_save;

import org.khasanof.post_service.dto.GenericDTO;
import org.khasanof.post_service.dto.post_save.PostSaveCreateDTO;
import org.khasanof.post_service.dto.post_save.PostSaveDeleteDTO;
import org.khasanof.post_service.exceptions.exceptions.InvalidValidationException;
import org.khasanof.post_service.validator.AbstractValidator;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PostSaveValidator extends AbstractValidator<PostSaveCreateDTO, GenericDTO, String> {

    @Override
    public void validCreateDTO(PostSaveCreateDTO postSaveCreateDTO) throws InvalidValidationException {
        if (Objects.isNull(postSaveCreateDTO)) {
            throw new InvalidValidationException("DTO is null");
        }
    }

    @Override
    public void validUpdateDTO(GenericDTO genericDTO) throws InvalidValidationException {

    }

    public void validDeleteDTO(PostSaveDeleteDTO dto) throws InvalidValidationException {
        if (Objects.isNull(dto)) {
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
