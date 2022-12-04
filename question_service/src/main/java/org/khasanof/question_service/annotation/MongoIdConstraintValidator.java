package org.khasanof.question_service.annotation;

import org.bson.types.ObjectId;
import org.khasanof.post_service.exceptions.exceptions.InvalidValidationException;

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
