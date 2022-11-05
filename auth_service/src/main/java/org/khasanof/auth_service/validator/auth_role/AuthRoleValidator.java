package org.khasanof.auth_service.validator.auth_role;

import org.bson.types.ObjectId;
import org.khasanof.auth_service.dto.GenericDTO;
import org.khasanof.auth_service.dto.auth_role.AuthRoleCreateDTO;
import org.khasanof.auth_service.exception.exceptions.InvalidValidationException;
import org.khasanof.auth_service.validator.AbstractValidator;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthRoleValidator extends AbstractValidator<AuthRoleCreateDTO, GenericDTO, String> {
    @Override
    public void validCreateDTO(AuthRoleCreateDTO authRoleCreateDTO) throws InvalidValidationException {
        if (Objects.isNull(authRoleCreateDTO)) {
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
        if (!ObjectId.isValid(s)) {
            throw new InvalidValidationException("Invalid ID!");
        }
    }
}
