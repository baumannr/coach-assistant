package hu.elte.webjava.coachassistant.application.webdomain;

import hu.elte.webjava.coachassistant.domain.Gender;
import hu.elte.webjava.coachassistant.domain.TrainingType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static hu.elte.webjava.coachassistant.application.common.AppConst.REGEXP_TRAINING_PLAN_NAME;
import static hu.elte.webjava.coachassistant.application.common.MsgKeys.*;

@Getter
@Setter
public class TrainingPlanDTO {
    private Integer id;
    @NotEmpty
    @Pattern(regexp = REGEXP_TRAINING_PLAN_NAME, message = "{" + VALIDATION_TRAINING_PLAN_NAME_PATTERN + "}")
    @Length(min = 3, max = 100, message = "{" + VALIDATION_TRAINING_PLAN_NAME_LENGTH + "}")
    private String name;
    @NotNull
    private TrainingType trainingType;
    @NotNull
    @Range(min = 1, max = 120, message = "{" + VALIDATION_TRAINING_PLAN_LENGTH_RANGE + "}")
    private Integer length;
    @NotNull
    @Range(min = 1, max = 1000, message = "{" + VALIDATION_TRAINING_PLAN_CALORIE_BURN_RANGE + "}")
    private Integer calorieBurn;
    @NotNull
    private Gender recommendation;
}
