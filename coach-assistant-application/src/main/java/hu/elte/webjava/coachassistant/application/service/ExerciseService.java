package hu.elte.webjava.coachassistant.application.service;

import hu.elte.webjava.coachassistant.domain.Exercise;

public interface ExerciseService {
    Exercise getExercise(int exerciseId);

    Exercise createExercise(Exercise exercise, int trainingPlanId);

    Exercise updateExercise(Exercise exercise);

    void deleteExercise(int exerciseId);
}
