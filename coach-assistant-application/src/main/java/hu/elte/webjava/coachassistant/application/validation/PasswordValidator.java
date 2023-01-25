package hu.elte.webjava.coachassistant.application.validation;

import hu.elte.webjava.coachassistant.application.common.AppConst;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null &&
            value.matches(AppConst.REGEXP_PASSWORD);
    }
}
