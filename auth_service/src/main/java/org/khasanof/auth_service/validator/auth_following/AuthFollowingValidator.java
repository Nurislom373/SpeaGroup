package org.khasanof.auth_service.validator.auth_following;

import org.khasanof.auth_service.dto.GenericDTO;
import org.khasanof.auth_service.dto.auth_following.AuthFollowingCreateDTO;
import org.khasanof.auth_service.exception.exceptions.InvalidValidationException;
import org.khasanof.auth_service.validator.AbstractValidator;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthFollowingValidator extends AbstractValidator<AuthFollowingCreateDTO, GenericDTO, String> {
    @Override
    public void validCreateDTO(AuthFollowingCreateDTO authFollowingCreateDTO) throws InvalidValidationException {
        if (Objects.isNull(authFollowingCreateDTO)) {
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
