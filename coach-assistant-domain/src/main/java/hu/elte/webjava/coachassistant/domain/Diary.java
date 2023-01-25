package hu.elte.webjava.coachassistant.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter @NoArgsConstructor
public abstract class Diary {
    @Id
    @GeneratedValue
    private Integer Id;
    private LocalDate createDate;
    @ManyToOne
    private Client client;
}
