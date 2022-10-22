package org.khasanof.auth_service.validator.auth_token;

import org.bson.types.ObjectId;
import org.khasanof.auth_service.dto.GenericDTO;
import org.khasanof.auth_service.dto.auth_token.AuthTokenCreateDTO;
import org.khasanof.auth_service.exception.exceptions.InvalidValidationException;
import org.khasanof.auth_service.validator.AbstractValidator;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthTokenValidator extends AbstractValidator<AuthTokenCreateDTO, GenericDTO, String> {
    @Override
    public void validCreateDTO(AuthTokenCreateDTO authTokenCreateDTO) throws InvalidValidationException {
        if (Objects.isNull(authTokenCreateDTO)) {
            throw new InvalidValidationException("DTO is null");
        }
        if (!ObjectId.isValid(authTokenCreateDTO.getAuthId())) {
            throw new InvalidValidationException("Invalid ID!");
        }
        if (authTokenCreateDTO.getMinTime() <= 1) {
            throw new InvalidValidationException("Invalid minTime!");
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
