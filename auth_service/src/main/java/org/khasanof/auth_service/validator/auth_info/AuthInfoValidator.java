package org.khasanof.auth_service.validator.auth_info;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.khasanof.auth_service.dto.auth_info.AuthInfoChangeVisibilityDTO;
import org.khasanof.auth_service.dto.auth_info.AuthInfoCreateDTO;
import org.khasanof.auth_service.dto.auth_info.AuthInfoUpdateDTO;
import org.khasanof.auth_service.dto.location.LocationCreateDTO;
import org.khasanof.auth_service.dto.location.LocationUpdateDTO;
import org.khasanof.auth_service.exception.exceptions.InvalidValidationException;
import org.khasanof.auth_service.validator.AbstractValidator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AuthInfoValidator extends AbstractValidator<AuthInfoCreateDTO, AuthInfoUpdateDTO, String> {
    @Override
    public void validCreateDTO(AuthInfoCreateDTO authInfoCreateDTO) throws InvalidValidationException {
        if (Objects.isNull(authInfoCreateDTO)) {
            throw new InvalidValidationException("DTO is null");
        }
        if (!ObjectId.isValid(authInfoCreateDTO.getAuthid())) {
            throw new InvalidValidationException("Invalid ID!");
        }
        Pattern pattern = Pattern.compile("^(\\\\+\\\\d{1,3}( )?)?((\\\\(\\\\d{3}\\\\))|\\\\d{3})[- .]?\\\\d{3}[- .]?\\\\d{4}$");
        if (Objects.nonNull(authInfoCreateDTO.getPhoneNumber())) {
            Matcher matcher = pattern.matcher(authInfoCreateDTO.getPhoneNumber());
            if (!matcher.matches()) {
                throw new InvalidValidationException("Invalid Phone Number!");
            }
        }
        if (Objects.nonNull(authInfoCreateDTO.getBornYearStr())) {
            String bornYearStr = authInfoCreateDTO.getBornYearStr();
            if (StringUtils.isNumeric(bornYearStr)) {
                int year = Integer.parseInt(bornYearStr);
                if (year < 1920 || year > 2022) {
                    throw new InvalidValidationException("Born year Invalid!");
                }
            }
        }
    }

    @Override
    public void validUpdateDTO(AuthInfoUpdateDTO authInfoUpdateDTO) throws InvalidValidationException {
        if (Objects.isNull(authInfoUpdateDTO)) {
            throw new InvalidValidationException("DTO is null");
        }
        if (!ObjectId.isValid(authInfoUpdateDTO.getId())) {
            throw new InvalidValidationException("Invalid ID!");
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

    public void validChangeVisibility(AuthInfoChangeVisibilityDTO dto) {
        if (Objects.isNull(dto)) {
            throw new InvalidValidationException("DTO is null");
        }
    }

    public void validKeys(List<String> ids) {
        ids.forEach((obj) -> {
            if (Objects.isNull(obj))
                throw new InvalidValidationException("ID is null");
        });
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
