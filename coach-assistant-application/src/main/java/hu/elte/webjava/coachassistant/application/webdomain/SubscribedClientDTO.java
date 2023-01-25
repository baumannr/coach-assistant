package hu.elte.webjava.coachassistant.application.webdomain;

import hu.elte.webjava.coachassistant.domain.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscribedClientDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer height;
    private Gender gender;
    private Integer age;
    private Double lastWeight;
    private Integer lastCalorie;
    private String subscribedTrainingPlanName;
}
