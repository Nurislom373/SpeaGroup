package org.khasanof.auth_service.validator.auth_block;

import org.khasanof.auth_service.dto.auth_block.AuthBlockCreateDTO;
import org.khasanof.auth_service.dto.auth_block.AuthBlockUpdateDTO;
import org.khasanof.auth_service.exception.exceptions.InvalidValidationException;
import org.khasanof.auth_service.validator.AbstractValidator;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthBlockValidator extends AbstractValidator<AuthBlockCreateDTO, AuthBlockUpdateDTO, String> {
    @Override
    public void validCreateDTO(AuthBlockCreateDTO authBlockCreateDTO) throws InvalidValidationException {
        if (Objects.isNull(authBlockCreateDTO)) {
            throw new InvalidValidationException("DTO is null");
        }
    }

    @Override
    public void validUpdateDTO(AuthBlockUpdateDTO authBlockUpdateDTO) throws InvalidValidationException {
        if (Objects.isNull(authBlockUpdateDTO)) {
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