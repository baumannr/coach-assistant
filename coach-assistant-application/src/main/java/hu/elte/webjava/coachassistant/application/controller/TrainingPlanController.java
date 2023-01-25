package hu.elte.webjava.coachassistant.application.controller;

import hu.elte.webjava.coachassistant.application.common.*;
import hu.elte.webjava.coachassistant.application.exception.EntityNotFoundException;
import hu.elte.webjava.coachassistant.application.security.SecurityHelper;
import hu.elte.webjava.coachassistant.application.service.ClientService;
import hu.elte.webjava.coachassistant.application.service.TrainingPlanService;
import hu.elte.webjava.coachassistant.application.transformer.ExerciseTransformer;
import hu.elte.webjava.coachassistant.application.transformer.TrainingPlanTransformer;
import hu.elte.webjava.coachassistant.application.webdomain.ExerciseDTO;
import hu.elte.webjava.coachassistant.application.webdomain.TrainingPlanDTO;
import hu.elte.webjava.coachassistant.domain.Gender;
import hu.elte.webjava.coachassistant.domain.TrainingPlan;
import hu.elte.webjava.coachassistant.domain.TrainingType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class TrainingPlanController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrainingPlanController.class);
    private static final String TRAINING_PLAN_DTO = "trainingPlanDTO";
    private static final String TRAINING_PLAN_PAGED_LIST_HOLDER = "trainingPlanPagedListHolder";
    private static final String EXERCISES_PAGED_LIST_HOLDER = "exercisePagedListHolder";
    private static final int PAGE_SIZE = 5;
    private static final String TRAINING_TYPES = "trainingTypes";
    private static final String GENDERS = "genders";
    private static final String CLIENT_SUBSCRIPTION_BUTTON = "clientSubscriptionButton";
    private final TrainingPlanService trainingPlanService;
    private final ClientService clientService;
    private final TrainingPlanTransformer trainingPlanTransformer;
    private final ExerciseTransformer exerciseTransformer;
    private final MessagesBundle messages;

    @Autowired
    public TrainingPlanController(TrainingPlanService trainingPlanService,
                                  TrainingPlanTransformer trainingPlanTransformer,
                                  ClientService clientService,
                                  ExerciseTransformer exerciseTransformer,
                                  MessagesBundle messages) {
        this.trainingPlanService = trainingPlanService;
        this.trainingPlanTransformer = trainingPlanTransformer;
        this.clientService = clientService;
        this.exerciseTransformer = exerciseTransformer;
        this.messages = messages;
    }

    @GetMapping(Mappings.GET_TRAINING_PLAN_INDEX)
    public String index(Model model, @RequestParam Optional<Integer> page) {
        List<TrainingPlanDTO> trainingPlans = getTrainingPlans();
        PagedListHolder<TrainingPlanDTO> trainingPlanPagedListHolder = new PagedListHolder<>(trainingPlans);
        trainingPlanPagedListHolder.setPage(page.orElse(0));
        trainingPlanPagedListHolder.setPageSize(PAGE_SIZE);
        model.addAttribute(TRAINING_PLAN_PAGED_LIST_HOLDER, trainingPlanPagedListHolder);
        return Views.TRAINING_PLAN_INDEX;
    }

    private List<TrainingPlanDTO> getTrainingPlans() {
        List<TrainingPlan> trainingPlans;

        int userId = SecurityHelper.getUserId();
        if (SecurityHelper.isCoach()) {
            trainingPlans = trainingPlanService.getTrainingPlanByCoachId(userId);
        } else {
            trainingPlans = trainingPlanService.getAllTrainingPlan();
            TrainingPlan trainingPlan = trainingPlanService.getTrainingPlanByClientId(userId);
            trainingPlans.remove(trainingPlan);
        }

        return trainingPlanTransformer.transformToTrainingPlanDTOList(trainingPlans);
    }

    @GetMapping( Mappings.GET_TRAINING_PLAN_DETAILS)
    public String details(Model model, @PathVariable("id") int trainingPlanId,
                          @RequestParam Optional<Integer> page) {
        TrainingPlan trainingPlan = trainingPlanService.getTrainingPlanById(trainingPlanId);
        if (trainingPlan == null) {
            LOGGER.error("No training plan found with id: {}", trainingPlanId);
            throw new EntityNotFoundException();
        }

        TrainingPlanDTO trainingPlanDTO = new TrainingPlanDTO();
        trainingPlanTransformer.transform(trainingPlan, trainingPlanDTO);
        PagedListHolder<ExerciseDTO> exercisePagedListHolder = getExercisePagedListHolder(trainingPlan, page);

        model.addAttribute(TRAINING_PLAN_DTO, trainingPlanDTO);
        model.addAttribute(EXERCISES_PAGED_LIST_HOLDER, exercisePagedListHolder);

        if (SecurityHelper.isClient()) {
            String subscriptionButtonModelAttributeValue = getSubscriptionButtonModelAttributeValue(trainingPlan);
            model.addAttribute(CLIENT_SUBSCRIPTION_BUTTON, subscriptionButtonModelAttributeValue);
        }

        return Views.TRAINING_PLAN_DETAILS;
    }

    private PagedListHolder<ExerciseDTO> getExercisePagedListHolder(TrainingPlan trainingPlan, Optional<Integer> page) {
        List<ExerciseDTO> exerciseDTOList = exerciseTransformer
                .transformToExerciseDTOList(trainingPlan.getExercises());
        PagedListHolder<ExerciseDTO> exercisePagedListHolder = new PagedListHolder<>(exerciseDTOList);
        exercisePagedListHolder.setPage(page.orElse(0));
        exercisePagedListHolder.setPageSize(PAGE_SIZE);
        return exercisePagedListHolder;
    }

    private String getSubscriptionButtonModelAttributeValue(TrainingPlan trainingPlan) {
        int clientId = SecurityHelper.getUserId();
        TrainingPlan subscribedTrainingPlan = trainingPlanService.getTrainingPlanByClientId(clientId);
        if (subscribedTrainingPlan == null) {
            return "subscribe";
        } else if (subscribedTrainingPlan.equals(trainingPlan)) {
            return "unsubscribe";
        } else {
            return "change";
        }
    }

    @GetMapping(Mappings.GET_TRAINING_PLAN_CREATE)
    public String create(Model model) {
        TrainingPlanDTO trainingPlanDTO = new TrainingPlanDTO();
        model.addAttribute(TRAINING_PLAN_DTO, trainingPlanDTO);
        return Views.TRAINING_PLAN_CREATE;
    }

    @PostMapping(Mappings.POST_TRAINING_PLAN_CREATE)
    public String create(@Valid TrainingPlanDTO trainingPlanDTO,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(AppConst.MESSAGE_WARNING, messages.getString(MsgKeys.TRAINING_PLAN_CREATE_WARN));
            return Views.TRAINING_PLAN_CREATE;
        }
        TrainingPlan trainingPlan = new TrainingPlan();
        trainingPlanTransformer.transform(trainingPlanDTO, trainingPlan);
        int coachId = SecurityHelper.getUserId();
        trainingPlanService.createTrainingPlan(coachId, trainingPlan);

        LOGGER.info("Training plan [id={}] successfully created!", trainingPlan.getId());
        redirectAttributes.addFlashAttribute(AppConst.MESSAGE_SUCCESS,
                messages.getString(MsgKeys.TRAINING_PLAN_CREATE_SUCCESS));
        return Views.REDIRECT_TRAINING_PLAN_DETAILS + trainingPlan.getId();
    }

    @GetMapping(Mappings.GET_TRAINING_PLAN_EDIT)
    public String edit(Model model, @PathVariable("id") int trainingPlanId) {
        TrainingPlan trainingPlan = trainingPlanService.getTrainingPlanById(trainingPlanId);
        if (trainingPlan == null) {
            LOGGER.error("No training plan with id: {}", trainingPlanId);
            throw new EntityNotFoundException();
        }

        TrainingPlanDTO trainingPlanDTO = new TrainingPlanDTO();
        trainingPlanTransformer.transform(trainingPlan, trainingPlanDTO);
        model.addAttribute(TRAINING_PLAN_DTO, trainingPlanDTO);

        return Views.TRAINING_PLAN_EDIT;
    }

    @PostMapping(Mappings.POST_TRAINING_PLAN_EDIT)
    public String edit(@Valid TrainingPlanDTO trainingPlanDTO,
                       BindingResult bindingResult,
                       Model model,
                       RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(AppConst.MESSAGE_WARNING, messages.getString(MsgKeys.TRAINING_PLAN_EDIT_WARN));
            return Views.TRAINING_PLAN_EDIT;
        }

        TrainingPlan trainingPlan = trainingPlanService.getTrainingPlanById(trainingPlanDTO.getId());
        trainingPlanTransformer.transform(trainingPlanDTO, trainingPlan);
        trainingPlanService.updateTrainingPlan(trainingPlan);

        LOGGER.info("Training plan [id={}] successfully updated!", trainingPlan.getId());
        redirectAttributes.addFlashAttribute(AppConst.MESSAGE_SUCCESS,
                messages.getString(MsgKeys.TRAINING_PLAN_EDIT_SUCCESS));

        return Views.REDIRECT_TRAINING_PLAN_DETAILS + trainingPlan.getId();
    }

    @PostMapping(Mappings.POST_TRAINING_PLAN_DELETE)
    public String delete(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        trainingPlanService.delete(id);

        LOGGER.info("Training plan [id={}] successfully deleted!", id);
        redirectAttributes.addFlashAttribute(AppConst.MESSAGE_SUCCESS,
                messages.getString(MsgKeys.TRAINING_PLAN_DELETE_SUCCESS));

        return Views.REDIRECT_TRAINING_PLAN_INDEX;
    }

    @PostMapping(Mappings.POST_TRAINING_PLAN_SUBSCRIBE)
    public String subscribe(@PathVariable("id") int trainingPlanId, RedirectAttributes redirectAttributes) {
        int clientId = SecurityHelper.getUserId();
        clientService.subscribeToTrainingPlan(clientId, trainingPlanId);

        LOGGER.info("Client [id={}] successfully subscribed to training plan [id={}]!", clientId, trainingPlanId);
        redirectAttributes.addFlashAttribute(AppConst.MESSAGE_SUCCESS,
                messages.getString(MsgKeys.CLIENT_SUBSCRIBE_SUCCESS));

        return Views.REDIRECT_CLIENT_DASHBOARD_INDEX;
    }

    @PostMapping(Mappings.POST_TRAINING_PLAN_UNSUBSCRIBE)
    public String unsubscribe(RedirectAttributes redirectAttributes) {
        int clientId = SecurityHelper.getUserId();
        TrainingPlan trainingPlan = trainingPlanService.getTrainingPlanByClientId(clientId);
        clientService.unsubscribeFromTrainingPlan(clientId);

        LOGGER.info("Client [id={}] successfully unsubscribed from training plan [id={}]!",
                clientId, trainingPlan.getId());
        redirectAttributes.addFlashAttribute(AppConst.MESSAGE_SUCCESS,
                messages.getString(MsgKeys.CLIENT_UNSUBSCRIBE_SUCCESS));

        return Views.REDIRECT_CLIENT_DASHBOARD_INDEX;
    }

    @PostMapping(Mappings.POST_TRAINING_PLAN_CHANGE_SUBSCRIPTION)
    public String changeSubscription(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        int clientId = SecurityHelper.getUserId();
        TrainingPlan oldTrainingPlan = trainingPlanService.getTrainingPlanByClientId(clientId);
        clientService.changeSubscription(clientId, id);

        LOGGER.info("Client [id={}] successfully changed subscription from training plan [id={}] " +
                        "to training plan [id={}]!", clientId, oldTrainingPlan.getId(), id);
        redirectAttributes.addFlashAttribute(AppConst.MESSAGE_SUCCESS,
                messages.getString(MsgKeys.CLIENT_SUBSCRIPTION_CHANGE_SUCCESS));

        return Views.REDIRECT_CLIENT_DASHBOARD_INDEX;
    }

    @ModelAttribute(TRAINING_TYPES)
    public Map<TrainingType, String> getTrainingTypes() {
        Map<TrainingType, String> trainingTypesMap = new LinkedHashMap<>();
        trainingTypesMap.put(TrainingType.STRENGTH, messages.getString(
                MsgKeys.TRAINING_TYPE_PREFIX + TrainingType.STRENGTH.toString().toLowerCase()));
        trainingTypesMap.put(TrainingType.CARDIO, messages.getString(
                MsgKeys.TRAINING_TYPE_PREFIX + TrainingType.CARDIO.toString().toLowerCase()));
        trainingTypesMap.put(TrainingType.CROSS_FIT, messages.getString(
                MsgKeys.TRAINING_TYPE_PREFIX + TrainingType.CROSS_FIT.toString().toLowerCase()));
        trainingTypesMap.put(TrainingType.FUNCTIONAL, messages.getString(
                MsgKeys.TRAINING_TYPE_PREFIX + TrainingType.FUNCTIONAL.toString().toLowerCase()));
        trainingTypesMap.put(TrainingType.HIIT, messages.getString(
                MsgKeys.TRAINING_TYPE_PREFIX + TrainingType.HIIT.toString().toLowerCase()));
        return trainingTypesMap;
    }

    @ModelAttribute(GENDERS)
    public Map<Gender, String> getGenders() {
        Map<Gender, String> gendersMap = new LinkedHashMap<>();
        gendersMap.put(Gender.MALE, messages.getString(
                MsgKeys.GENDER_PREFIX + Gender.MALE.toString().toLowerCase()));
        gendersMap.put(Gender.FEMALE, messages.getString(
                MsgKeys.GENDER_PREFIX + Gender.FEMALE.toString().toLowerCase()));
        return gendersMap;
    }
}
