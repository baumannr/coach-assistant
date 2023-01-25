package hu.elte.webjava.coachassistant.application.controller;

import hu.elte.webjava.coachassistant.application.common.Mappings;
import hu.elte.webjava.coachassistant.application.common.Views;
import hu.elte.webjava.coachassistant.application.security.SecurityHelper;
import hu.elte.webjava.coachassistant.application.service.CalorieDiaryService;
import hu.elte.webjava.coachassistant.application.service.TrainingPlanService;
import hu.elte.webjava.coachassistant.application.service.WeightDiaryService;
import hu.elte.webjava.coachassistant.application.transformer.CalorieDiaryTransformer;
import hu.elte.webjava.coachassistant.application.transformer.TrainingPlanTransformer;
import hu.elte.webjava.coachassistant.application.transformer.WeightDiaryTransformer;
import hu.elte.webjava.coachassistant.application.webdomain.CalorieDiaryDTO;
import hu.elte.webjava.coachassistant.application.webdomain.TrainingPlanDTO;
import hu.elte.webjava.coachassistant.application.webdomain.WeightDiaryDTO;
import hu.elte.webjava.coachassistant.domain.TrainingPlan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ClientDashboardController {

    private static final String TRAINING_PLAN_DTO = "trainingPlanDTO";
    private static final String CALORIE_DIARY_DTO_LIST = "calorieDiaryDTOList";
    private static final String WEIGHT_DIARY_DTO_LIST = "weightDiaryDTOList";
    private final CalorieDiaryService calorieDiaryService;
    private final WeightDiaryService weightDiaryService;
    private final TrainingPlanService trainingPlanService;
    private final CalorieDiaryTransformer calorieDiaryTransformer;
    private final WeightDiaryTransformer weightDiaryTransformer;
    private final TrainingPlanTransformer trainingPlanTransformer;

    public ClientDashboardController(CalorieDiaryService calorieDiaryService,
                                     WeightDiaryService weightDiaryService,
                                     TrainingPlanService trainingPlanService,
                                     CalorieDiaryTransformer calorieDiaryTransformer,
                                     WeightDiaryTransformer weightDiaryTransformer,
                                     TrainingPlanTransformer trainingPlanTransformer) {
        this.calorieDiaryService = calorieDiaryService;
        this.weightDiaryService = weightDiaryService;
        this.trainingPlanService = trainingPlanService;
        this.calorieDiaryTransformer = calorieDiaryTransformer;
        this.weightDiaryTransformer = weightDiaryTransformer;
        this.trainingPlanTransformer = trainingPlanTransformer;
    }

    @GetMapping(Mappings.GET_CLIENT_DASHBOARD_INDEX)
    public String index(Model model) {
        int clientId = SecurityHelper.getUserId();
        TrainingPlan trainingPlan = trainingPlanService.getTrainingPlanByClientId(clientId);
        TrainingPlanDTO trainingPlanDTO = null;

        if (trainingPlan != null) {
            trainingPlanDTO = new TrainingPlanDTO();
            trainingPlanTransformer.transform(trainingPlan, trainingPlanDTO);
        }

        List<CalorieDiaryDTO> calorieDiaryDTOList = calorieDiaryTransformer
                .transformToCalorieDiaryDTOList(calorieDiaryService.getDiariesByClientId(clientId, 14));

        List<WeightDiaryDTO> weightDiaryDTOList = weightDiaryTransformer
                .transformToWeightDiaryDTOList(weightDiaryService.getDiariesByClientId(clientId, 14));

        model.addAttribute(TRAINING_PLAN_DTO, trainingPlanDTO);
        model.addAttribute(CALORIE_DIARY_DTO_LIST, calorieDiaryDTOList);
        model.addAttribute(WEIGHT_DIARY_DTO_LIST, weightDiaryDTOList);

        return Views.CLIENT_DASHBOARD_INDEX;
    }
}
