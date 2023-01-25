package hu.elte.webjava.coachassistant.application.webdomain;

import hu.elte.webjava.coachassistant.application.validation.MaxDateConstraint;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static hu.elte.webjava.coachassistant.application.common.MsgKeys.VALIDATION_WEIGHT_DIARY_CALORIE_RANGE;

@Getter
@Setter
public class WeightDiaryDTO {
    private Integer Id;
    @NotNull
    @MaxDateConstraint
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate createDate;
    @NotNull
    @Range(min = 1, max = 300, message = "{" + VALIDATION_WEIGHT_DIARY_CALORIE_RANGE + "}")
    private Double weight;
}
