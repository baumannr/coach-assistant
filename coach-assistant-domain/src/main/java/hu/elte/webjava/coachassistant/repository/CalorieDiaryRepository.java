package hu.elte.webjava.coachassistant.repository;

import hu.elte.webjava.coachassistant.domain.CalorieDiary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CalorieDiaryRepository extends CrudRepository<CalorieDiary, Integer> {
    List<CalorieDiary> findByClientIdOrderByCreateDateDesc(int clientId);

    List<CalorieDiary> findByClientIdOrderByCreateDateDesc(int clientId, Pageable pageable);
}
