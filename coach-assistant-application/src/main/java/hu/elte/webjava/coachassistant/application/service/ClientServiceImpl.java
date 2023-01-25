package hu.elte.webjava.coachassistant.application.service;

import hu.elte.webjava.coachassistant.domain.Client;
import hu.elte.webjava.coachassistant.domain.Subscription;
import hu.elte.webjava.coachassistant.domain.TrainingPlan;
import hu.elte.webjava.coachassistant.repository.ClientRepository;
import hu.elte.webjava.coachassistant.repository.SubscriptionRepository;
import hu.elte.webjava.coachassistant.repository.TrainingPlanRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
public class ClientServiceImpl implements ClientService {

    private final TrainingPlanRepository trainingPlanRepository;

    private final ClientRepository clientRepository;

    private final SubscriptionRepository subscriptionRepository;

    public ClientServiceImpl(TrainingPlanRepository trainingPlanRepository,
                             ClientRepository clientRepository,
                             SubscriptionRepository subscriptionRepository) {
        this.trainingPlanRepository = trainingPlanRepository;
        this.clientRepository = clientRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public void subscribeToTrainingPlan(int clientId, int trainingPlanId) {
        TrainingPlan trainingPlan = trainingPlanRepository.findById(trainingPlanId).orElse(null);
        Client client = clientRepository.findById(clientId).orElse(null);
        Subscription subscription = new Subscription();
        subscription.setClient(client);
        subscription.setTrainingPlan(trainingPlan);
        subscription.setSubscriptionDate(LocalDate.now());
        subscriptionRepository.save(subscription);
    }

    @Override
    public void unsubscribeFromTrainingPlan(int clientId) {
        Subscription subscription = subscriptionRepository.findByClientId(clientId);
        subscriptionRepository.delete(subscription);
    }

    @Override
    public boolean isSubscribed(int clientId) {
        return subscriptionRepository.existsByClientId(clientId);
    }

    @Override
    @Transactional
    public void changeSubscription(int clientId, int trainingPlanId) {
        unsubscribeFromTrainingPlan(clientId);
        subscribeToTrainingPlan(clientId, trainingPlanId);
    }

    @Override
    public Client getClient(int clientId) {
        return clientRepository.findById(clientId).get();
    }
}