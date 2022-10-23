package org.khasanof.auth_service.validator.employment;

import org.bson.types.ObjectId;
import org.khasanof.auth_service.dto.employment.EmploymentCreateDTO;
import org.khasanof.auth_service.dto.employment.EmploymentUpdateDTO;
import org.khasanof.auth_service.exception.exceptions.InvalidValidationException;
import org.khasanof.auth_service.utils.DateValidator;
import org.khasanof.auth_service.validator.AbstractValidator;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Locale;
import java.util.Objects;

@Component
public class EmploymentValidator extends AbstractValidator<EmploymentCreateDTO, EmploymentUpdateDTO, String> {

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu", Locale.US).withResolverStyle(ResolverStyle.STRICT);
    private final DateValidator validator = new DateValidator(dateFormatter);

    @Override
    public void validCreateDTO(EmploymentCreateDTO employmentCreateDTO) throws InvalidValidationException {
        if (Objects.isNull(employmentCreateDTO)) {
            throw new InvalidValidationException("DTO is null");
        }
        if (!ObjectId.isValid(employmentCreateDTO.getInfoId())) {
            throw new InvalidValidationException("Invalid InfoId!");
        }
        if (!validator.isValid(employmentCreateDTO.getStartYearStr()) || !validator.isValid(employmentCreateDTO.getEndYearStr())) {
            throw new InvalidValidationException("Invalid Date!");
        }
    }

    @Override
    public void validUpdateDTO(EmploymentUpdateDTO employmentUpdateDTO) throws InvalidValidationException {
        if (Objects.isNull(employmentUpdateDTO)) {
            throw new InvalidValidationException("DTO is null");
        }
        if (!ObjectId.isValid(employmentUpdateDTO.getInfoId())) {
            throw new InvalidValidationException("Invalid InfoId!");
        }
        if (!validator.isValid(employmentUpdateDTO.getStartYearStr()) || !validator.isValid(employmentUpdateDTO.getEndYearStr())) {
            throw new InvalidValidationException("Invalid Date!");
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
