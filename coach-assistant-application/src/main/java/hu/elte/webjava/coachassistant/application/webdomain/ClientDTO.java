package hu.elte.webjava.coachassistant.application.webdomain;

import hu.elte.webjava.coachassistant.application.validation.MaxDateConstraint;
import hu.elte.webjava.coachassistant.domain.Gender;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

import static hu.elte.webjava.coachassistant.application.common.AppConst.REGEXP_USER_NAME;
import static hu.elte.webjava.coachassistant.application.common.MsgKeys.*;

@Getter
@Setter
public class ClientDTO {
    private Integer id;
    @Pattern(regexp = REGEXP_USER_NAME, message = "{" + VALIDATION_FIRST_NAME_PATTERN + "}")
    private String firstName;
    @Pattern(regexp = REGEXP_USER_NAME, message = "{" + VALIDATION_LAST_NAME_PATTERN + "}")
    private String lastName;
    @NotNull
    @MaxDateConstraint
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birth;
    @NotNull
    @Range(min = 1, max = 300, message = "{" + VALIDATION_CLIENT_HEIGHT_RANGE + "}")
    private Integer height;
    @NotNull
    private Gender gender;
    private String email;
}
