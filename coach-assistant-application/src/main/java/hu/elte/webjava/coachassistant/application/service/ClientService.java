package hu.elte.webjava.coachassistant.application.service;

import hu.elte.webjava.coachassistant.domain.Client;
import hu.elte.webjava.coachassistant.domain.TrainingPlan;

public interface ClientService {
    void subscribeToTrainingPlan(int clientId, int trainingPlanId);

    void unsubscribeFromTrainingPlan(int clientId);

    void changeSubscription(int clientId, int trainingPlanId);

    boolean isSubscribed(int clientId);

    Client getClient(int clientId);
}
