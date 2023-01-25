package hu.elte.webjava.coachassistant.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter @Setter @NoArgsConstructor
public class WeightDiary extends Diary {
    private Double weight;
}
