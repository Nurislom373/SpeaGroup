package org.khasanof.post_service.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateConstraintValidator.class)
@Documented
public @interface DateConstraint {

    String message() default "Invalid.Date";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
