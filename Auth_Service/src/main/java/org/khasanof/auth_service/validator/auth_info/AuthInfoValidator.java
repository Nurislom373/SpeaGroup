package org.khasanof.auth_service.validator.auth_info;

import org.khasanof.auth_service.dto.auth_info.AuthInfoCreateDTO;
import org.khasanof.auth_service.dto.auth_info.AuthInfoUpdateDTO;
import org.khasanof.auth_service.dto.location.LocationCreateDTO;
import org.khasanof.auth_service.dto.location.LocationUpdateDTO;
import org.khasanof.auth_service.exception.exceptions.InvalidValidationException;
import org.khasanof.auth_service.validator.AbstractValidator;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthInfoValidator extends AbstractValidator<AuthInfoCreateDTO, AuthInfoUpdateDTO, String> {
    @Override
    public void validCreateDTO(AuthInfoCreateDTO authInfoCreateDTO) throws InvalidValidationException {
        if (Objects.isNull(authInfoCreateDTO)) {
            throw new InvalidValidationException("DTO is null");
        }
    }

    @Override
    public void validUpdateDTO(AuthInfoUpdateDTO authInfoUpdateDTO) throws InvalidValidationException {
        if (Objects.isNull(authInfoUpdateDTO)) {
            throw new InvalidValidationException("DTO is null");
        }
    }

    @Override
    public void validKey(String s) throws InvalidValidationException {
        if (Objects.isNull(s)) {
            throw new InvalidValidationException("ID is null");
        }
    }

    public void validLocationCreateDTO(LocationCreateDTO dto) throws InvalidValidationException {
        if (Objects.isNull(dto)) {
            throw new InvalidValidationException("DTO is null");
        }
    }

    public void validLocationUpdateDTO(LocationUpdateDTO dto) throws InvalidValidationException {
        if (Objects.isNull(dto)) {
            throw new InvalidValidationException("DTO is null");
        }
    }
}
