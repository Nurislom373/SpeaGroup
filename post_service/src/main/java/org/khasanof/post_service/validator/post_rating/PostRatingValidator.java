package org.khasanof.post_service.validator.post_rating;

import org.khasanof.post_service.dto.GenericDTO;
import org.khasanof.post_service.dto.post_rating.PostRatingCreateDTO;
import org.khasanof.post_service.exceptions.exceptions.InvalidValidationException;
import org.khasanof.post_service.validator.AbstractValidator;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PostRatingValidator extends AbstractValidator<PostRatingCreateDTO, GenericDTO, String> {

    @Override
    public void validCreateDTO(PostRatingCreateDTO postRatingCreateDTO) throws InvalidValidationException {
        if (Objects.isNull(postRatingCreateDTO)) {
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
