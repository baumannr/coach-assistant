package hu.elte.webjava.coachassistant.repository;

import hu.elte.webjava.coachassistant.domain.WeightDiary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WeightDiaryRepository extends CrudRepository<WeightDiary, Integer> {
    List<WeightDiary> findByClientIdOrderByCreateDateDesc(int clientId);

    List<WeightDiary> findByClientIdOrderByCreateDateDesc(int clientId, Pageable pageable);
}
