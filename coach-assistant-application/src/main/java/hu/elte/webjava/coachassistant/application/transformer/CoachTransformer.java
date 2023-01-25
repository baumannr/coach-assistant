package hu.elte.webjava.coachassistant.application.transformer;

import hu.elte.webjava.coachassistant.application.webdomain.CoachDTO;
import hu.elte.webjava.coachassistant.application.webdomain.TrainingPlanDTO;
import hu.elte.webjava.coachassistant.domain.Coach;
import hu.elte.webjava.coachassistant.domain.TrainingPlan;
import org.springframework.stereotype.Component;

@Component
public class CoachTransformer {
    public void transform(Coach coach, CoachDTO dto) {
        dto.setId(coach.getId());
        dto.setFirstName(coach.getFirstName());
        dto.setLastName(coach.getLastName());
        dto.setEmail(coach.getEmail());
    }

    public void transform (CoachDTO dto, Coach coach) {
        coach.setId(dto.getId());
        coach.setFirstName(dto.getFirstName());
        coach.setLastName(dto.getLastName());
        coach.setEmail(dto.getEmail());
    }
}
