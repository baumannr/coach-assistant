package hu.elte.webjava.coachassistant.application.factory;

import hu.elte.webjava.coachassistant.application.exception.UnsupportedExerciseTypeException;
import hu.elte.webjava.coachassistant.application.webdomain.ExerciseDTO;
import hu.elte.webjava.coachassistant.domain.Exercise;
import hu.elte.webjava.coachassistant.domain.IntervalExercise;
import hu.elte.webjava.coachassistant.domain.RepetitiveExercise;
import org.springframework.stereotype.Component;

@Component
public class ExerciseFactory {

    public Exercise getExercise(ExerciseDTO dto) {
        switch (dto.getExerciseType()) {
            case REPETITIVE:
                return map(dto, new RepetitiveExercise());
            case INTERVAL:
                return map(dto, new IntervalExercise());
            default:
                throw new UnsupportedExerciseTypeException("Unsupported exercise type: "
                                                           + dto.getExerciseType());
        }
    }

    private RepetitiveExercise map(ExerciseDTO dto, RepetitiveExercise repetitiveExercise) {
        map(dto, (Exercise) repetitiveExercise);
        repetitiveExercise.setRepetitions(dto.getRepetitions());
        return repetitiveExercise;
    }

    private IntervalExercise map(ExerciseDTO dto, IntervalExercise intervalExercise) {
        map(dto, (Exercise) intervalExercise);
        intervalExercise.setLength(dto.getLength());
        intervalExercise.setLengthTimeUnit(dto.getLengthTimeUnit());
        return intervalExercise;
    }

    private void map(ExerciseDTO dto, Exercise exercise) {
        exercise.setId(dto.getId());
        exercise.setName(dto.getName());
        exercise.setSets(dto.getSets());
        exercise.setRest(dto.getRest());
        exercise.setRestTimeUnit(dto.getRestTimeUnit());
    }
}
