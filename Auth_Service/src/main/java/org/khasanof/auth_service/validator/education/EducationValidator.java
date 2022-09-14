package org.khasanof.auth_service.validator.education;

import org.khasanof.auth_service.dto.education.EducationCreateDTO;
import org.khasanof.auth_service.dto.education.EducationUpdateDTO;
import org.khasanof.auth_service.exception.exceptions.InvalidValidationException;
import org.khasanof.auth_service.validator.AbstractValidator;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.util.Objects;

@Component
public class EducationValidator extends AbstractValidator<EducationCreateDTO, EducationUpdateDTO, String> {
    @Override
    public void validCreateDTO(EducationCreateDTO dto) throws InvalidValidationException {
        if (Objects.isNull(dto)) {
            throw new NotFoundException("DTO is null");
        }
    }

    @Override
    public void validUpdateDTO(EducationUpdateDTO dto) throws InvalidValidationException {
        if (Objects.isNull(dto)) {
            throw new NotFoundException("DTO is null");
        }
    }

    @Override
    public void validKey(String s) throws InvalidValidationException {
        if (Objects.isNull(s)) {
            throw new NotFoundException("ID is null");
        }
    }
}
