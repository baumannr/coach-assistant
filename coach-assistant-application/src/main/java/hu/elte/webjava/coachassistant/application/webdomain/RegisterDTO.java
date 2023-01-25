package hu.elte.webjava.coachassistant.application.webdomain;

import hu.elte.webjava.coachassistant.application.validation.PasswordConfirmationConstraint;
import hu.elte.webjava.coachassistant.application.validation.PasswordConstraint;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static hu.elte.webjava.coachassistant.application.common.AppConst.REGEXP_USER_NAME;
import static hu.elte.webjava.coachassistant.application.common.MsgKeys.*;

@Getter
@Setter
@PasswordConfirmationConstraint
public class RegisterDTO {
    @Pattern(regexp = REGEXP_USER_NAME, message = "{" + VALIDATION_FIRST_NAME_PATTERN + "}")
    private String firstName;
    @Pattern(regexp = REGEXP_USER_NAME, message = "{" + VALIDATION_LAST_NAME_PATTERN + "}")
    private String lastName;
    @NotEmpty
    @Email
    private String email;
    @PasswordConstraint
    private String password;
    private String confirmPassword;
    @NotNull
    private UserType userType;
}
