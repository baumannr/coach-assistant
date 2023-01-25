package hu.elte.webjava.coachassistant.application.service;

import hu.elte.webjava.coachassistant.domain.TrainingPlan;

import java.util.List;

public interface TrainingPlanService {
    List<TrainingPlan> getTrainingPlanByCoachId(int coachId);

    List<TrainingPlan> getAllTrainingPlan();

    TrainingPlan getTrainingPlanById(int trainingPlanId);

    TrainingPlan getTrainingPlanByClientId(int clientId);

    TrainingPlan createTrainingPlan(int coachId, TrainingPlan trainingPlan);

    TrainingPlan updateTrainingPlan(TrainingPlan trainingPlan);

    void delete(int trainingPlanId);
}