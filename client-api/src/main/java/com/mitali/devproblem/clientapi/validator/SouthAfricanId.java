package com.mitali.devproblem.clientapi.validator;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(
        validatedBy = {SouthAfricanIdValidator.class}
)
public @interface SouthAfricanId {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
