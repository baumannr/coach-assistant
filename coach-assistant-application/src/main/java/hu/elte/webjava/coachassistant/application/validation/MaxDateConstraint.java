package hu.elte.webjava.coachassistant.application.validation;

import hu.elte.webjava.coachassistant.application.common.MsgKeys;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MaxDateValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MaxDateConstraint {
    String message() default "{" + MsgKeys.VALIDATION_MAX_DATE + "}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
