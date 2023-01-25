package hu.elte.webjava.coachassistant.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @Setter @NoArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue
    private Integer id;
    private LocalDate subscriptionDate;
    @OneToOne
    private Client client;
    @ManyToOne
    private TrainingPlan trainingPlan;
}
