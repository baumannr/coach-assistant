package hu.elte.webjava.coachassistant.repository;

import org.springframework.data.repository.CrudRepository;
import hu.elte.webjava.coachassistant.domain.Client;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Integer> {
    List<Client> findBySubscriptionTrainingPlanCoachId(int coachId);
}
