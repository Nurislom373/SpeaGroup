package org.khasanof.auth_service.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsMongoIdValidator.class)
@Documented
public @interface IsMongoId {

    String message() default "Invalid.Username";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
