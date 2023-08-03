package com.krekerok.user.util.validator.impl;

import com.krekerok.user.util.validator.PasswordInequality;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class PasswordInequalityValidator implements ConstraintValidator<PasswordInequality, Object> {
    private String currentPassword;
    private String newPassword;
    private String message;

    public void initialize(PasswordInequality constraintAnnotation) {
        this.currentPassword = constraintAnnotation.currentPassword();
        this.newPassword = constraintAnnotation.newPassword();
        this.message = constraintAnnotation.message();
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object currentPasswordValue = new BeanWrapperImpl(value).getPropertyValue(currentPassword);
        Object newPasswordValue = new BeanWrapperImpl(value).getPropertyValue(newPassword);

        boolean isValid = currentPasswordValue != null && !currentPasswordValue.equals(newPasswordValue);
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context
                .buildConstraintViolationWithTemplate(message)
                .addPropertyNode(newPassword)
                .addConstraintViolation();
        }
        return isValid;
    }
}
