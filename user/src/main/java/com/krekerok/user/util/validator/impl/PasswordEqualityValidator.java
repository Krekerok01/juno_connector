package com.krekerok.user.util.validator.impl;

import com.krekerok.user.util.validator.PasswordEquality;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class PasswordEqualityValidator implements ConstraintValidator<PasswordEquality, Object> {

    private String password;
    private String confirmationPassword;
    private String message;

    public void initialize(PasswordEquality constraintAnnotation) {
        this.password = constraintAnnotation.password();
        this.confirmationPassword = constraintAnnotation.confirmationPassword();
        this.message = constraintAnnotation.message();
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object passwordValue = new BeanWrapperImpl(value).getPropertyValue(password);
        Object confirmationPasswordValue = new BeanWrapperImpl(value).getPropertyValue(confirmationPassword);

        boolean isValid = passwordValue != null && passwordValue.equals(confirmationPasswordValue);
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context
                .buildConstraintViolationWithTemplate(message)
                .addPropertyNode(confirmationPassword)
                .addConstraintViolation();
        }
        return isValid;
    }
}
