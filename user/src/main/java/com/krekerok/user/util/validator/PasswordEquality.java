package com.krekerok.user.util.validator;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.krekerok.user.util.validator.impl.PasswordEqualityValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordEqualityValidator.class)
@Documented
public @interface PasswordEquality {

    String message() default "Fields values does not equals";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String password();

    String confirmationPassword();

    @Target(TYPE)
    @Retention(RUNTIME)
    @interface List {
        PasswordEquality[] value();
    }
}