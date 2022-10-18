package org.khasanof.auth_service.validator.auth_user;

import org.bson.types.ObjectId;
import org.khasanof.auth_service.dto.auth_user.AuthUserCreateDTO;
import org.khasanof.auth_service.dto.auth_user.AuthUserUpdateDTO;
import org.khasanof.auth_service.exception.exceptions.InvalidValidationException;
import org.khasanof.auth_service.validator.AbstractValidator;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthUserValidator extends AbstractValidator<AuthUserCreateDTO, AuthUserUpdateDTO, String> {
    @Override
    public void validCreateDTO(AuthUserCreateDTO authUserCreateDTO) throws InvalidValidationException {
        if (Objects.isNull(authUserCreateDTO)) {
            throw new InvalidValidationException("DTO is null");
        }
    }

    @Override
    public void validUpdateDTO(AuthUserUpdateDTO authUserUpdateDTO) throws InvalidValidationException {
        if (Objects.isNull(authUserUpdateDTO)) {
            throw new InvalidValidationException("DTO is null");
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
