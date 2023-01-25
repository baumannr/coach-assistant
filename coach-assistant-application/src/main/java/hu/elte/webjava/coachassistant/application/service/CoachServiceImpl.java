package hu.elte.webjava.coachassistant.application.service;

import hu.elte.webjava.coachassistant.domain.Client;
import hu.elte.webjava.coachassistant.domain.Coach;
import hu.elte.webjava.coachassistant.repository.ClientRepository;
import hu.elte.webjava.coachassistant.repository.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoachServiceImpl implements CoachService {
    private final ClientRepository clientRepository;
    private final CoachRepository coachRepository;

    @Autowired
    public CoachServiceImpl(ClientRepository ClientRepository, CoachRepository coachRepository) {
        this.clientRepository = ClientRepository;
        this.coachRepository = coachRepository;
    }

    @Override
    public List<Client> getClientsSubscribedToCoachByCoachId(int coachId) {
        return clientRepository.findBySubscriptionTrainingPlanCoachId(coachId);
    }

    @Override
    public Coach getCoach(int coachId){
        Coach coach = coachRepository.findById(coachId).get();
        return coach;
    }
}