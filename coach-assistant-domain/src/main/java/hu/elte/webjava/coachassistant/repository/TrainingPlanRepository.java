package hu.elte.webjava.coachassistant.repository;

import hu.elte.webjava.coachassistant.domain.TrainingPlan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrainingPlanRepository extends CrudRepository<TrainingPlan, Integer> {
    TrainingPlan findByCoachId(int coachId);
    TrainingPlan findBySubscriptionClientId(int clientId);
    List<TrainingPlan> findAllByCoachId(int coachId);
    int countByCoachId(int coachId);
}