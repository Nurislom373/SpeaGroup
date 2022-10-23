package org.khasanof.auth_service.annotation;

import org.khasanof.auth_service.utils.DateValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Locale;

public class DateConstraintValidator implements ConstraintValidator<DateConstraint, String> {

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu", Locale.US)
            .withResolverStyle(ResolverStyle.STRICT);
    private final DateValidator validator = new DateValidator(dateFormatter);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!validator.isValid(value)) {
            throw new RuntimeException("Invalid Date!");
        }
        return true;
    }

}
