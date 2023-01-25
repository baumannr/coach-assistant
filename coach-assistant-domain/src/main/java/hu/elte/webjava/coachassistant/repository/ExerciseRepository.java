package hu.elte.webjava.coachassistant.repository;

import hu.elte.webjava.coachassistant.domain.Exercise;
import org.springframework.data.repository.CrudRepository;

public interface ExerciseRepository extends CrudRepository<Exercise, Integer> {
}
