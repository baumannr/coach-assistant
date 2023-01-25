package hu.elte.webjava.coachassistant.application.service;

import hu.elte.webjava.coachassistant.domain.Client;
import hu.elte.webjava.coachassistant.domain.Coach;

import java.util.List;
import java.util.Optional;

public interface CoachService {
    List<Client> getClientsSubscribedToCoachByCoachId(int coachId);

    Coach getCoach(int coachId);
}
