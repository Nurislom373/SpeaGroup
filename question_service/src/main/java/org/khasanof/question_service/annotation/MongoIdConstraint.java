package org.khasanof.question_service.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MongoIdConstraintValidator.class)
@Documented
public @interface MongoIdConstraint {

    String message() default "Invalid.Username";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
