package org.khasanof.auth_service.annotation;

import org.bson.types.ObjectId;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsMongoIdValidator implements ConstraintValidator<IsMongoId, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return ObjectId.isValid(value);
    }

}
