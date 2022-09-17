package org.khasanof.auth_service.validator.employment;

import org.khasanof.auth_service.dto.employment.EmploymentCreateDTO;
import org.khasanof.auth_service.dto.employment.EmploymentUpdateDTO;
import org.khasanof.auth_service.exception.exceptions.InvalidValidationException;
import org.khasanof.auth_service.validator.AbstractValidator;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class EmploymentValidator extends AbstractValidator<EmploymentCreateDTO, EmploymentUpdateDTO, String> {

    @Override
    public void validCreateDTO(EmploymentCreateDTO employmentCreateDTO) throws InvalidValidationException {
        if (Objects.isNull(employmentCreateDTO)) {
            throw new InvalidValidationException("DTO is null");
        }
    }

    @Override
    public void validUpdateDTO(EmploymentUpdateDTO employmentUpdateDTO) throws InvalidValidationException {
        if (Objects.isNull(employmentUpdateDTO)) {
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
