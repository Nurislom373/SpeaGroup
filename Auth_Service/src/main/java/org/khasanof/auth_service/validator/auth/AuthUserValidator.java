package org.khasanof.auth_service.validator.auth;

import org.khasanof.auth_service.dto.auth.AuthUserCreateDTO;
import org.khasanof.auth_service.dto.auth.AuthUserUpdateDTO;
import org.khasanof.auth_service.exception.exceptions.InvalidValidationException;
import org.khasanof.auth_service.validator.AbstractValidator;
import org.khasanof.auth_service.validator.Validator;
import org.springframework.stereotype.Component;

@Component
public class AuthUserValidator extends AbstractValidator<AuthUserCreateDTO, AuthUserUpdateDTO,String> {
    @Override
    public void validCreateDTO(AuthUserCreateDTO authUserCreateDTO) throws InvalidValidationException {

    }

    @Override
    public void validUpdateDTO(AuthUserUpdateDTO authUserUpdateDTO) throws InvalidValidationException {

    }

    @Override
    public void validKey(String s) throws InvalidValidationException {

    }
}
