package hu.elte.webjava.coachassistant.application.service;

import hu.elte.webjava.coachassistant.domain.DomainUser;

public interface UserService {
    DomainUser update(DomainUser user);
    
    DomainUser register(DomainUser user);

}
