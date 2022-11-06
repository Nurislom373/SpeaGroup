package org.khasanof.auth_service.validator.blocked_for;

import org.bson.types.ObjectId;
import org.khasanof.auth_service.dto.blocked_for.BlockedForCreateDTO;
import org.khasanof.auth_service.dto.blocked_for.BlockedForUpdateDTO;
import org.khasanof.auth_service.exception.exceptions.InvalidValidationException;
import org.khasanof.auth_service.validator.AbstractValidator;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class BlockedForValidator extends AbstractValidator<BlockedForCreateDTO, BlockedForUpdateDTO, String> {
    @Override
    public void validCreateDTO(BlockedForCreateDTO blockedForCreateDTO) throws InvalidValidationException {
        if (Objects.isNull(blockedForCreateDTO)) {
            throw new InvalidValidationException("DTO is null");
        }
    }

    @Override
    public void validUpdateDTO(BlockedForUpdateDTO blockedForUpdateDTO) throws InvalidValidationException {
        if (Objects.isNull(blockedForUpdateDTO)) {
            throw new InvalidValidationException("DTO is null");
        }
        if (!ObjectId.isValid(blockedForUpdateDTO.getId())) {
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
