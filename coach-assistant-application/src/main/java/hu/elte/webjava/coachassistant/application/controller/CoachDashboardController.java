package hu.elte.webjava.coachassistant.application.controller;

import hu.elte.webjava.coachassistant.application.common.Mappings;
import hu.elte.webjava.coachassistant.application.common.Views;
import hu.elte.webjava.coachassistant.application.security.SecurityHelper;
import hu.elte.webjava.coachassistant.application.service.CoachService;
import hu.elte.webjava.coachassistant.application.service.TrainingPlanService;
import hu.elte.webjava.coachassistant.application.transformer.ClientTransformer;
import hu.elte.webjava.coachassistant.application.webdomain.SubscribedClientDTO;
import hu.elte.webjava.coachassistant.domain.Client;
import hu.elte.webjava.coachassistant.domain.TrainingPlan;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class CoachDashboardController {

    private static final String SUBSCRIBED_CLIENTS_PAGED_LIST_HOLDER = "subscribedClientsPagedListHolder";
    private final static String ANY_TRAINING_PLAN = "anyTrainingPlan";
    private final static String TRAINING_PLAN_TREND_CHART_DATA = "trainingPlanTrendChartData";
    private static final int PAGE_SIZE = 5;
    private final CoachService coachService;
    private final TrainingPlanService trainingPlanService;
    private final ClientTransformer clientTransformer;

    public CoachDashboardController(CoachService coachService,
                                    TrainingPlanService trainingPlanService,
                                    ClientTransformer clientTransformer) {
        this.coachService = coachService;
        this.trainingPlanService = trainingPlanService;
        this.clientTransformer = clientTransformer;
    }

    @GetMapping(Mappings.GET_COACH_DASHBOARD_INDEX)
    public String index(Model model, @RequestParam Optional<Integer> page) {
        int coachId = SecurityHelper.getUserId();
        List<Client> clients = coachService.getClientsSubscribedToCoachByCoachId(coachId);
        List<TrainingPlan> trainingPlans = trainingPlanService.getTrainingPlanByCoachId(coachId);

        PagedListHolder<SubscribedClientDTO> pagedListHolder = getSubscribedClientDTOPagedListHolder(clients, page);
        Map<String, Integer> trainingPlanTrendChartData = getTrainingPlansTrendChartData(trainingPlans, clients);
        boolean anyTrainingPlan = trainingPlans.size() > 0;

        model.addAttribute(SUBSCRIBED_CLIENTS_PAGED_LIST_HOLDER, pagedListHolder);
        model.addAttribute(TRAINING_PLAN_TREND_CHART_DATA, trainingPlanTrendChartData);
        model.addAttribute(ANY_TRAINING_PLAN, anyTrainingPlan);

        return Views.COACH_DASHBOARD_INDEX;
    }

    private PagedListHolder<SubscribedClientDTO> getSubscribedClientDTOPagedListHolder(List<Client> clientList,
                                                                                       Optional<Integer> page) {
        List<SubscribedClientDTO> clientDTOList = clientTransformer.transformToSubscribedClientDTOList(clientList);
        PagedListHolder<SubscribedClientDTO> pagedListHolder = new PagedListHolder<>(clientDTOList);
        pagedListHolder.setPage(page.orElse(0));
        pagedListHolder.setPageSize(PAGE_SIZE);
        return pagedListHolder;
    }

    private Map<String, Integer> getTrainingPlansTrendChartData(List<TrainingPlan> trainingPlans,
                                                                List<Client> clients) {
        Map<String, Integer> trainingPlanTrendChartData = trainingPlans.stream()
                .collect(Collectors.toMap(TrainingPlan::getName, t -> 0));

        for (Client client : clients) {
            TrainingPlan trainingPlan = client.getSubscription().getTrainingPlan();
            trainingPlanTrendChartData.merge(trainingPlan.getName(), 1, Integer::sum);
        }

        return trainingPlanTrendChartData;
    }
}
