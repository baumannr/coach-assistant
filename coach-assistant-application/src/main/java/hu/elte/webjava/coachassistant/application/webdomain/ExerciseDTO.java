package hu.elte.webjava.coachassistant.application.webdomain;

import hu.elte.webjava.coachassistant.application.validation.ExerciseDTOConstraint;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.concurrent.TimeUnit;

import static hu.elte.webjava.coachassistant.application.common.MsgKeys.*;

@Getter
@Setter
@ExerciseDTOConstraint
public class ExerciseDTO {
    private Integer id;
    private Integer trainingPlanId;
    @NotEmpty
    @Length(min = 3, max = 100, message = "{" + VALIDATION_EXERCISE_NAME_LENGTH + "}")
    private String name;
    @NotNull
    @Range(min = 1, max = 10, message = "{" + VALIDATION_EXERCISE_SETS_RANGE + "}")
    private Integer sets;
    private Integer rest;
    private TimeUnit restTimeUnit;
    private ExerciseType exerciseType;
    private Integer repetitions;
    private Integer length;
    private TimeUnit lengthTimeUnit;
}
