package hu.elte.webjava.coachassistant.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter @NoArgsConstructor
public class DomainUser {
    @Id
    @GeneratedValue
    private int id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
