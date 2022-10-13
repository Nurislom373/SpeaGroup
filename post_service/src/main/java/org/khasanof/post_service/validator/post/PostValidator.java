package org.khasanof.post_service.validator.post;

import org.bson.types.ObjectId;
import org.khasanof.post_service.dto.post.PostCreateDTO;
import org.khasanof.post_service.dto.post.PostUpdateDTO;
import org.khasanof.post_service.enums.post.PostVisibilityEnum;
import org.khasanof.post_service.exceptions.exceptions.InvalidValidationException;
import org.khasanof.post_service.validator.AbstractValidator;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PostValidator extends AbstractValidator<PostCreateDTO, PostUpdateDTO, String> {
    @Override
    public void validCreateDTO(PostCreateDTO postCreateDTO) throws InvalidValidationException {
        if (Objects.isNull(postCreateDTO)) {
            throw new InvalidValidationException("DTO is null");
        }
        if (!PostVisibilityEnum.hasVisibility(postCreateDTO.getVisibility())) {
            throw new RuntimeException("Visibility Invalid!");
        }
    }

    @Override
    public void validUpdateDTO(PostUpdateDTO postUpdateDTO) throws InvalidValidationException {
        if (Objects.isNull(postUpdateDTO)) {
            throw new InvalidValidationException("DTO is null");
        }
        if (!PostVisibilityEnum.hasVisibility(postUpdateDTO.getVisibility())) {
            throw new RuntimeException("Visibility Invalid!");
        }
    }

    @Override
    public void validKey(String s) throws InvalidValidationException {
        if (Objects.isNull(s)) {
            throw new InvalidValidationException("ID is null");
        }
        if (!ObjectId.isValid(s)) {
            throw new InvalidValidationException("Invalid Id!");
        }
    }
}
