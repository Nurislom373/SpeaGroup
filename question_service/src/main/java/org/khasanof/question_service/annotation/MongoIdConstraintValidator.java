package org.khasanof.question_service.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.bson.types.ObjectId;
import org.khasanof.question_service.exception.exceptions.InvalidValidationException;

public class MongoIdConstraintValidator implements ConstraintValidator<MongoIdConstraint, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!ObjectId.isValid(value)) {
            throw new InvalidValidationException("Invalid ID!");
        }
        return true;
    }

}
