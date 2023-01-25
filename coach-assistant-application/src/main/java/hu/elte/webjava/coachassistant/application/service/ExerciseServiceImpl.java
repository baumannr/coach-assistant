package hu.elte.webjava.coachassistant.application.service;

import hu.elte.webjava.coachassistant.domain.Exercise;
import hu.elte.webjava.coachassistant.domain.TrainingPlan;
import hu.elte.webjava.coachassistant.repository.ExerciseRepository;
import hu.elte.webjava.coachassistant.repository.TrainingPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;

    private final TrainingPlanRepository trainingPlanRepository;

    @Autowired
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, TrainingPlanRepository trainingPlanRepository) {
        this.exerciseRepository = exerciseRepository;
        this.trainingPlanRepository = trainingPlanRepository;
    }

    @Override
    public Exercise getExercise(int exerciseId) {
        return exerciseRepository.findById(exerciseId).orElse(null);
    }

    @Override
    public Exercise createExercise(Exercise exercise, int trainingPlanId) {
        TrainingPlan trainingPlan = trainingPlanRepository.findById(trainingPlanId).orElse(null);
        exercise.setTrainingPlan(trainingPlan);
        return exerciseRepository.save(exercise);
    }

    @Override
    public Exercise updateExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    @Override
    public void deleteExercise(int exerciseId) {
        exerciseRepository.deleteById(exerciseId);
    }
}