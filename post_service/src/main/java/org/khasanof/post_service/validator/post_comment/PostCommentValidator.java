package org.khasanof.post_service.validator.post_comment;

import org.khasanof.post_service.dto.post_comment.PostCommentAddLikeDTO;
import org.khasanof.post_service.dto.post_comment.PostCommentCreateDTO;
import org.khasanof.post_service.dto.post_comment.PostCommentRemoveLikeDTO;
import org.khasanof.post_service.exceptions.exceptions.InvalidValidationException;
import org.khasanof.post_service.validator.AbstractValidator;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PostCommentValidator extends AbstractValidator<PostCommentCreateDTO, PostCommentAddLikeDTO, String> {
    @Override
    public void validCreateDTO(PostCommentCreateDTO postCommentCreateDTO) throws InvalidValidationException {
        if (Objects.isNull(postCommentCreateDTO)) {
            throw new InvalidValidationException("DTO is null");
        }
    }

    @Override
    public void validUpdateDTO(PostCommentAddLikeDTO postCommentAddLikeDTO) throws InvalidValidationException {
        if (Objects.isNull(postCommentAddLikeDTO)) {
            throw new InvalidValidationException("DTO is null");
        }
    }

    public void validCommentRemoveDTO(PostCommentRemoveLikeDTO dto) throws InvalidValidationException {
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
