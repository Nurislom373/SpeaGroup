package org.khasanof.post_service.validator.post_like;

import org.khasanof.post_service.dto.GenericDTO;
import org.khasanof.post_service.dto.post_like.PostLikeCreateDTO;
import org.khasanof.post_service.dto.post_like.PostLikeDeleteDTO;
import org.khasanof.post_service.dto.post_like.PostLikeDetailDTO;
import org.khasanof.post_service.exceptions.exceptions.InvalidValidationException;
import org.khasanof.post_service.validator.AbstractValidator;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PostLikeValidator extends AbstractValidator<PostLikeCreateDTO, GenericDTO, String> {
    @Override
    public void validCreateDTO(PostLikeCreateDTO postLikeCreateDTO) throws InvalidValidationException {
        if (Objects.isNull(postLikeCreateDTO)) {
            throw new InvalidValidationException("DTO is null");
        }
    }

    @Override
    public void validUpdateDTO(GenericDTO genericDTO) throws InvalidValidationException {

    }

    public void validDeleteDTO(PostLikeDeleteDTO dto) throws InvalidValidationException {
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
