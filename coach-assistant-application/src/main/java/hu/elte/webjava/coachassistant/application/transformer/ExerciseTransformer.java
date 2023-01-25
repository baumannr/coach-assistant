package hu.elte.webjava.coachassistant.application.transformer;

import hu.elte.webjava.coachassistant.application.webdomain.ExerciseDTO;
import hu.elte.webjava.coachassistant.application.webdomain.ExerciseType;
import hu.elte.webjava.coachassistant.domain.Exercise;
import hu.elte.webjava.coachassistant.domain.IntervalExercise;
import hu.elte.webjava.coachassistant.domain.RepetitiveExercise;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExerciseTransformer {

    public List<ExerciseDTO> transformToExerciseDTOList(List<Exercise> exerciseList) {
        return exerciseList.stream().map(exercise -> {
            ExerciseDTO dto = new ExerciseDTO();
            this.transform(exercise, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    public void transform(Exercise exercise, ExerciseDTO dto) {
        dto.setId(exercise.getId());
        dto.setTrainingPlanId(exercise.getTrainingPlan().getId());
        dto.setName(exercise.getName());
        dto.setSets(exercise.getSets());
        dto.setRest(exercise.getRest());
        dto.setRestTimeUnit(exercise.getRestTimeUnit());

        if (exercise instanceof RepetitiveExercise) {
            transform((RepetitiveExercise) exercise, dto);
        } else if (exercise instanceof IntervalExercise) {
            transform((IntervalExercise) exercise, dto);
        }
    }

    private void transform(RepetitiveExercise exercise, ExerciseDTO dto) {
        dto.setRepetitions(exercise.getRepetitions());
        dto.setExerciseType(ExerciseType.REPETITIVE);
    }

    private void transform(IntervalExercise exercise, ExerciseDTO dto) {
        dto.setLength(exercise.getLength());
        dto.setLengthTimeUnit(exercise.getLengthTimeUnit());
        dto.setExerciseType(ExerciseType.INTERVAL);
    }

    public void transform(ExerciseDTO dto, Exercise exercise) {
        exercise.setId(dto.getId());
        exercise.setName(dto.getName());
        exercise.setSets(dto.getSets());
        exercise.setRest(dto.getRest());
        exercise.setRestTimeUnit(dto.getRestTimeUnit());

        if (exercise instanceof RepetitiveExercise) {
            transform(dto, (RepetitiveExercise) exercise);
        } else if (exercise instanceof IntervalExercise) {
            transform(dto, (IntervalExercise) exercise);
        }
    }

    private void transform(ExerciseDTO dto, RepetitiveExercise exercise) {
        exercise.setRepetitions(dto.getRepetitions());
    }

    private void transform(ExerciseDTO dto, IntervalExercise exercise) {
        exercise.setLength(dto.getLength());
        exercise.setLengthTimeUnit(dto.getLengthTimeUnit());
    }
}
