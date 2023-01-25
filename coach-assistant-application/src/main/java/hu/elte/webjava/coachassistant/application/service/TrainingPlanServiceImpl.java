package hu.elte.webjava.coachassistant.application.service;

import hu.elte.webjava.coachassistant.domain.Coach;
import hu.elte.webjava.coachassistant.domain.TrainingPlan;
import hu.elte.webjava.coachassistant.repository.CoachRepository;
import hu.elte.webjava.coachassistant.repository.TrainingPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingPlanServiceImpl implements TrainingPlanService {

    private final TrainingPlanRepository trainingPlanRepository;

    private final CoachRepository coachRepository;

    @Autowired
    public TrainingPlanServiceImpl(TrainingPlanRepository trainingPlanRepository, CoachRepository coachRepository) {
        this.trainingPlanRepository = trainingPlanRepository;
        this.coachRepository = coachRepository;
    }

    @Override
    public List<TrainingPlan> getTrainingPlanByCoachId(int coachId) {
        return trainingPlanRepository.findAllByCoachId(coachId);
    }

    @Override
    public List<TrainingPlan> getAllTrainingPlan() {
        return (List<TrainingPlan>)trainingPlanRepository.findAll();
    }

    @Override
    public TrainingPlan getTrainingPlanByClientId(int clientId) {
        return trainingPlanRepository.findBySubscriptionClientId(clientId);
    }

    @Override
    public TrainingPlan getTrainingPlanById(int trainingPlanId) {
        return trainingPlanRepository.findById(trainingPlanId).orElse(null);
    }


    @Override
    public TrainingPlan createTrainingPlan(int coachId, TrainingPlan trainingPlan) {
        Coach coach = coachRepository.findById(coachId).orElse(null);
        trainingPlan.setCoach(coach);
        return trainingPlanRepository.save(trainingPlan);
    }

    @Override
    public TrainingPlan updateTrainingPlan(TrainingPlan trainingPlan) {
        return trainingPlanRepository.save(trainingPlan);
    }

    @Override
    public void delete(int trainingPlanId) {
        trainingPlanRepository.deleteById(trainingPlanId);
    }
}