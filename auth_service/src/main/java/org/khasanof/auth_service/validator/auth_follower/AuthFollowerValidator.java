package org.khasanof.auth_service.validator.auth_follower;

import org.bson.types.ObjectId;
import org.khasanof.auth_service.dto.GenericDTO;
import org.khasanof.auth_service.dto.auth_follower.AuthFollowerCreateDTO;
import org.khasanof.auth_service.exception.exceptions.InvalidValidationException;
import org.khasanof.auth_service.validator.AbstractValidator;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthFollowerValidator extends AbstractValidator<AuthFollowerCreateDTO, GenericDTO, String> {
    @Override
    public void validCreateDTO(AuthFollowerCreateDTO authFollowerCreateDTO) throws InvalidValidationException {
        if (Objects.isNull(authFollowerCreateDTO)) {
            throw new InvalidValidationException("DTO is null");
        }
        Boolean orElse = authFollowerCreateDTO.getFollowerId()
                .stream()
                .map(ObjectId::isValid)
                .filter(f -> !f)
                .findFirst().orElse(true);
        if (!ObjectId.isValid(authFollowerCreateDTO.getAuthId()) || !orElse) {
            throw new InvalidValidationException("Invalid ID!");
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
        if (!ObjectId.isValid(s)) {
            throw new InvalidValidationException("Invalid ID!");
        }
    }
}
