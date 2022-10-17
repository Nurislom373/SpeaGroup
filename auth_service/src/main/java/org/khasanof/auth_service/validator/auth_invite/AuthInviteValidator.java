package org.khasanof.auth_service.validator.auth_invite;

import org.khasanof.auth_service.dto.GenericDTO;
import org.khasanof.auth_service.dto.auth_invite.AuthInviteCreateDTO;
import org.khasanof.auth_service.exception.exceptions.InvalidValidationException;
import org.khasanof.auth_service.validator.AbstractValidator;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthInviteValidator extends AbstractValidator<AuthInviteCreateDTO, GenericDTO, String> {

    @Override
    public void validCreateDTO(AuthInviteCreateDTO authInviteCreateDTO) throws InvalidValidationException {
        if (Objects.isNull(authInviteCreateDTO)) {
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
        if ()
    }

}
