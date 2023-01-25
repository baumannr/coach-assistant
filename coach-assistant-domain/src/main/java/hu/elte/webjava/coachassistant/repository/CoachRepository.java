package hu.elte.webjava.coachassistant.repository;

import org.springframework.data.repository.CrudRepository;
import hu.elte.webjava.coachassistant.domain.Coach;

public interface CoachRepository extends CrudRepository<Coach,Integer> {
}
