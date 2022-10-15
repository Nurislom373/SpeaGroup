package org.khasanof.post_service.validator.post_block;

import org.bson.types.ObjectId;
import org.khasanof.post_service.dto.post_block.PostBlockCreateDTO;
import org.khasanof.post_service.dto.post_block.PostBlockUpdateDTO;
import org.khasanof.post_service.exceptions.exceptions.InvalidValidationException;
import org.khasanof.post_service.validator.AbstractValidator;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PostBlockValidator extends AbstractValidator<PostBlockCreateDTO, PostBlockUpdateDTO, String> {

    @Override
    public void validCreateDTO(PostBlockCreateDTO postBlockCreateDTO) throws InvalidValidationException {
        if (Objects.isNull(postBlockCreateDTO)) {
            throw new InvalidValidationException("DTO is null");
        }
        if (!ObjectId.isValid(postBlockCreateDTO.getBlockedPostId())) {
            throw new InvalidValidationException("Invalid ID!");
        }
    }

    @Override
    public void validUpdateDTO(PostBlockUpdateDTO postBlockUpdateDTO) throws InvalidValidationException {
        if (Objects.isNull(postBlockUpdateDTO)) {
            throw new InvalidValidationException("DTO is null");
        }
        if (!ObjectId.isValid(postBlockUpdateDTO.getId())) {
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
