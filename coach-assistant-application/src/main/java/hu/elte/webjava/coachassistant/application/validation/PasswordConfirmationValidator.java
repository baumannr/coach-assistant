package hu.elte.webjava.coachassistant.application.validation;

import hu.elte.webjava.coachassistant.application.webdomain.RegisterDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class PasswordConfirmationValidator implements ConstraintValidator<PasswordConfirmationConstraint, RegisterDTO> {

    String message;
    String fieldName;

    @Override
    public void initialize(PasswordConfirmationConstraint constraintAnnotation) {
        message = constraintAnnotation.message();
        fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(RegisterDTO registerDto, ConstraintValidatorContext context) {
        boolean isValid = registerDto.getPassword() == null ||
                Objects.equals(registerDto.getPassword(), "") ||
                registerDto.getPassword().equals(registerDto.getConfirmPassword());
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                   .addPropertyNode(fieldName)
                   .addConstraintViolation();
        }
        return isValid;
    }
}
