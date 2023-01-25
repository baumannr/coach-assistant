package hu.elte.webjava.coachassistant.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.concurrent.TimeUnit;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter @NoArgsConstructor
public abstract class Exercise {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Integer sets;
    private Integer rest;
    private TimeUnit restTimeUnit;
    @ManyToOne
    private TrainingPlan trainingPlan;
}
