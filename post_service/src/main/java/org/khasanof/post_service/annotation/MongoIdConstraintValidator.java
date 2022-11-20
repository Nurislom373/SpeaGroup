package org.khasanof.post_service.annotation;

import org.bson.types.ObjectId;
import org.khasanof.auth_service.exception.exceptions.InvalidValidationException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MongoIdConstraintValidator implements ConstraintValidator<MongoIdConstraint, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!ObjectId.isValid(value)) {
            throw new InvalidValidationException("Invalid ID!");
        }
        return true;
    }

}
