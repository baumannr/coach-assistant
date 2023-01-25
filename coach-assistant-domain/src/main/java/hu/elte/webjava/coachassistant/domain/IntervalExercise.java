package hu.elte.webjava.coachassistant.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.concurrent.TimeUnit;

@Entity
@Getter @Setter @NoArgsConstructor
public class IntervalExercise extends Exercise {
    private Integer length;
    private TimeUnit lengthTimeUnit;
}
