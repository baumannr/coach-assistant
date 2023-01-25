package hu.elte.webjava.coachassistant.application.validation;

import hu.elte.webjava.coachassistant.application.common.MsgKeys;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = PasswordConfirmationValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConfirmationConstraint {

    String fieldName() default "confirmPassword";

    String message() default "{" + MsgKeys.VALIDATION_PASSWORD_CONFIRMATION + "}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
