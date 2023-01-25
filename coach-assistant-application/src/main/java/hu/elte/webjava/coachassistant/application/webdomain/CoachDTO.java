package hu.elte.webjava.coachassistant.application.webdomain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import static hu.elte.webjava.coachassistant.application.common.AppConst.REGEXP_USER_NAME;
import static hu.elte.webjava.coachassistant.application.common.MsgKeys.*;

@Getter
@Setter
public class CoachDTO {
    private Integer Id;
    @Pattern(regexp = REGEXP_USER_NAME, message = "{" + VALIDATION_FIRST_NAME_PATTERN + "}")
    private String firstName;
    @Pattern(regexp = REGEXP_USER_NAME, message = "{" + VALIDATION_LAST_NAME_PATTERN + "}")
    private String lastName;
    private String email;
}
