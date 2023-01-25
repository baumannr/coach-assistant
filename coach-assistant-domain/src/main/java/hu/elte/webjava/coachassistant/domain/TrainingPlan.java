package hu.elte.webjava.coachassistant.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class TrainingPlan {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @Enumerated(EnumType.STRING)
    private TrainingType trainingType;
    private Integer length;
    private Integer calorieBurn;
    @Enumerated(EnumType.STRING)
    private Gender recommendation;
    @ManyToOne
    private Coach coach;
    @OneToMany(mappedBy = "trainingPlan", cascade = {CascadeType.REMOVE})
    private List<Exercise> exercises;
    @OneToMany(mappedBy = "trainingPlan", cascade = {CascadeType.REMOVE})
    private List<Subscription> subscription;
}
