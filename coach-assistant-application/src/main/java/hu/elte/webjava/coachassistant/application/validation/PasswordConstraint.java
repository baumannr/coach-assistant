package hu.elte.webjava.coachassistant.application.validation;

import hu.elte.webjava.coachassistant.application.common.MsgKeys;

import javax.persistence.Temporal;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConstraint {

    String message() default "{" + MsgKeys.VALIDATION_PASSWORD + "}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
