package org.khasanof.auth_service.validator.auth_user;

import org.bson.types.ObjectId;
import org.khasanof.auth_service.dto.auth_user.AuthUserCreateDTO;
import org.khasanof.auth_service.dto.auth_user.AuthUserUpdateDTO;
import org.khasanof.auth_service.enums.language.LanguageEnums;
import org.khasanof.auth_service.exception.exceptions.InvalidValidationException;
import org.khasanof.auth_service.validator.AbstractValidator;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        if (!ObjectId.isValid(authUserUpdateDTO.getId())) {
            throw new InvalidValidationException("Invalid ID!");
        }
        if (!LanguageEnums.hasLang(authUserUpdateDTO.getLanguage())) {
            throw new InvalidValidationException("Invalid Language!");
        }
        Pattern pattern = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@\" \n" +
                                          "+ \"[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$");
        if (!pattern.matcher(authUserUpdateDTO.getEmail()).matches()) {
            throw new InvalidValidationException("Invalid Email!");
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
