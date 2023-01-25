package hu.elte.webjava.coachassistant.repository;

import hu.elte.webjava.coachassistant.domain.Subscription;
import org.springframework.data.repository.CrudRepository;

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {
    Subscription findByClientId(int clientId);
    boolean existsByClientId(int clientId);
}
