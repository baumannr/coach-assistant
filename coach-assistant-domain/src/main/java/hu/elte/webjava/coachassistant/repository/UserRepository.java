package hu.elte.webjava.coachassistant.repository;

import hu.elte.webjava.coachassistant.domain.DomainUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<DomainUser, Integer> {
    DomainUser findByEmail(String email);
}
