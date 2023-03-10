package hu.elte.webjava.coachassistant.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class Coach extends DomainUser {
    @OneToMany(mappedBy = "coach")
    private List<TrainingPlan> trainingPlans;
}
