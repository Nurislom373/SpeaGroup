package org.khasanof.post_service.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
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
