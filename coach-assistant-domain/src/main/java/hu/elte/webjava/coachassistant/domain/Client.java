package hu.elte.webjava.coachassistant.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class Client extends DomainUser {
    private LocalDate birth;
    private Integer height;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @OneToOne(mappedBy = "client")
    private Subscription subscription;
    @OneToMany(mappedBy = "client")
    private List<Diary> diaries;
}

