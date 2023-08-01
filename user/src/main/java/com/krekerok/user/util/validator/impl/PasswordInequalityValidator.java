package com.krekerok.user.util.validator.impl;

import com.krekerok.user.util.validator.PasswordInequality;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class PasswordInequalityValidator implements ConstraintValidator<PasswordInequality, Object> {
    private String oldPassword;
    private String newPassword;
    private String message;

    public void initialize(PasswordInequality constraintAnnotation) {
        this.oldPassword = constraintAnnotation.oldPassword();
        this.newPassword = constraintAnnotation.newPassword();
        this.message = constraintAnnotation.message();
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object oldPasswordValue = new BeanWrapperImpl(value).getPropertyValue(oldPassword);
        Object newPasswordValue = new BeanWrapperImpl(value).getPropertyValue(newPassword);

        boolean isValid = oldPasswordValue != null && !oldPasswordValue.equals(newPasswordValue);
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
