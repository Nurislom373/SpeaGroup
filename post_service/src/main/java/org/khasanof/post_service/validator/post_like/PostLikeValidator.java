package org.khasanof.post_service.validator.post_like;

import org.bson.types.ObjectId;
import org.khasanof.post_service.dto.GenericDTO;
import org.khasanof.post_service.dto.post_like.PostLikeCreateDTO;
import org.khasanof.post_service.dto.post_like.PostLikeDeleteDTO;
import org.khasanof.post_service.dto.post_like.PostLikeDetailDTO;
import org.khasanof.post_service.enums.like.LikeTypeEnum;
import org.khasanof.post_service.exceptions.exceptions.InvalidValidationException;
import org.khasanof.post_service.validator.AbstractValidator;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.util.Objects;

@Component
public class PostLikeValidator extends AbstractValidator<PostLikeCreateDTO, GenericDTO, String> {

    @Override
    public void validCreateDTO(PostLikeCreateDTO postLikeCreateDTO) throws InvalidValidationException {
        if (Objects.isNull(postLikeCreateDTO)) {
            throw new InvalidValidationException("DTO is null");
        }
        if (!LikeTypeEnum.hasLikeType(postLikeCreateDTO.getType())) {
            throw new NotFoundException("Like type Invalid");
        }
        if (!ObjectId.isValid(postLikeCreateDTO.getLikePostId())) {
            throw new InvalidValidationException("Invalid ID!");
        }
    }

    @Override
    public void validUpdateDTO(GenericDTO genericDTO) throws InvalidValidationException {

    }

    public void validDeleteDTO(PostLikeDeleteDTO dto) throws InvalidValidationException {
        if (Objects.isNull(dto)) {
            throw new InvalidValidationException("DTO is null");
        }
        if (!ObjectId.isValid(dto.getLikePostId()) || !ObjectId.isValid(dto.getUserId())) {
            throw new InvalidValidationException("Invalid ID!");
        }
    }

    public void validLikeTypeCount(String id, String type) throws InvalidValidationException {
        if (Objects.isNull(id) || Objects.isNull(type)) {
            throw new InvalidValidationException("Parameter is null");
        }
        if (!LikeTypeEnum.hasLikeType(type)) {
            throw new InvalidValidationException("Like Invalid type!");
        }
        if (!ObjectId.isValid(id)) {
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
