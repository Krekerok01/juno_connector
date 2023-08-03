package com.krekerok.user.util.validator;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.krekerok.user.util.validator.impl.PasswordInequalityValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordInequalityValidator.class)
@Documented
public @interface PasswordInequality {
    String message() default "The current password and the new password cannot be the same.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String currentPassword();
    String newPassword();
}