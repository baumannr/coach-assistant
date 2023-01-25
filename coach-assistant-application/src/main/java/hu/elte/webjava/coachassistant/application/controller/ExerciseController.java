package hu.elte.webjava.coachassistant.application.controller;

import hu.elte.webjava.coachassistant.application.common.*;
import hu.elte.webjava.coachassistant.application.exception.EntityNotFoundException;
import hu.elte.webjava.coachassistant.application.exception.UnsupportedExerciseTypeException;
import hu.elte.webjava.coachassistant.application.factory.ExerciseFactory;
import hu.elte.webjava.coachassistant.application.service.ExerciseService;
import hu.elte.webjava.coachassistant.application.transformer.ExerciseTransformer;
import hu.elte.webjava.coachassistant.application.webdomain.ExerciseDTO;
import hu.elte.webjava.coachassistant.application.webdomain.ExerciseType;
import hu.elte.webjava.coachassistant.domain.Exercise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
public class ExerciseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExerciseController.class);
    private static final String EXERCISE_DTO = "exerciseDTO";
    private static final String EXERCISE_TYPES = "exerciseTypes";
    private static final String TIME_UNITS = "timeUnits";
    private final ExerciseService exerciseService;
    private final ExerciseFactory exerciseFactory;
    private final ExerciseTransformer exerciseTransformer;
    private final MessagesBundle messages;

    @Autowired
    public ExerciseController(ExerciseService exerciseService,
                              ExerciseFactory exerciseFactory,
                              ExerciseTransformer exerciseTransformer,
                              MessagesBundle messages) {
        this.exerciseService = exerciseService;
        this.exerciseFactory = exerciseFactory;
        this.exerciseTransformer = exerciseTransformer;
        this.messages = messages;
    }

    @GetMapping(Mappings.GET_EXERCISE_CREATE)
    public String create(Model model, @PathVariable("trainingPlanId") int trainingPlanId) {
        ExerciseDTO exerciseDTO = new ExerciseDTO();
        exerciseDTO.setTrainingPlanId(trainingPlanId);
        exerciseDTO.setExerciseType(ExerciseType.REPETITIVE);
        model.addAttribute(EXERCISE_DTO, exerciseDTO);

        return Views.EXERCISE_CREATE;
    }

    @PostMapping(Mappings.POST_EXERCISE_CREATE)
    public String create(@Valid ExerciseDTO exerciseDTO,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(AppConst.MESSAGE_WARNING, messages.getString(MsgKeys.EXERCISE_CREATE_WARN));
            return Views.EXERCISE_CREATE;
        }

        Exercise exercise = exerciseFactory.getExercise(exerciseDTO);
        exerciseService.createExercise(exercise, exerciseDTO.getTrainingPlanId());

        LOGGER.info("Exercise [id={}] successfully created!", exercise.getId());
        redirectAttributes.addFlashAttribute(AppConst.MESSAGE_SUCCESS,
                messages.getString(MsgKeys.EXERCISE_CREATE_SUCCESS));

        return Views.REDIRECT_TRAINING_PLAN_DETAILS + exerciseDTO.getTrainingPlanId();
    }

    @GetMapping(Mappings.GET_EXERCISE_EDIT)
    public String edit(Model model, @PathVariable("id") int exerciseId) {
        Exercise exercise = exerciseService.getExercise(exerciseId);
        if (exercise == null) {
            LOGGER.error("No exercise found with id: {}", exerciseId);
            throw new EntityNotFoundException();
        }

        ExerciseDTO exerciseDTO = new ExerciseDTO();
        exerciseTransformer.transform(exercise, exerciseDTO);
        model.addAttribute(EXERCISE_DTO, exerciseDTO);

        return Views.EXERCISE_EDIT;
    }

    @PostMapping(Mappings.POST_EXERCISE_EDIT)
    public String edit(@Valid ExerciseDTO exerciseDTO,
                       BindingResult bindingResult,
                       Model model,
                       RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(AppConst.MESSAGE_WARNING, messages.getString(MsgKeys.EXERCISE_EDIT_WARN));
            return Views.EXERCISE_EDIT;
        }

        Exercise exercise = exerciseService.getExercise(exerciseDTO.getId());
        exerciseTransformer.transform(exerciseDTO, exercise);
        exerciseService.updateExercise(exercise);

        LOGGER.info("Exercise [id={}] successfully updated!", exercise.getId());
        redirectAttributes.addFlashAttribute(AppConst.MESSAGE_SUCCESS,
                messages.getString(MsgKeys.EXERCISE_EDIT_SUCCESS));

        return Views.REDIRECT_TRAINING_PLAN_DETAILS + exercise.getTrainingPlan().getId();
    }

    @PostMapping(Mappings.POST_EXERCISE_DELETE)
    public String delete(@PathVariable("id") int exerciseId, RedirectAttributes redirectAttributes) {
        Exercise exercise = exerciseService.getExercise(exerciseId);
        if (exercise == null) {
            LOGGER.error("No exercise found with id: {}", exerciseId);
            throw new EntityNotFoundException();
        }
        exerciseService.deleteExercise(exerciseId);

        LOGGER.info("Exercise [id={}] successfully updated!", exercise.getId());
        redirectAttributes.addFlashAttribute(AppConst.MESSAGE_SUCCESS,
                messages.getString(MsgKeys.EXERCISE_DELETE_SUCCESS));

        return Views.REDIRECT_TRAINING_PLAN_DETAILS + exercise.getTrainingPlan().getId();
    }

    @ModelAttribute(EXERCISE_TYPES)
    public Map<ExerciseType, String> getExerciseTypes() {
        LinkedHashMap<ExerciseType, String> exerciseTypes = new LinkedHashMap<>();
        exerciseTypes.put(ExerciseType.REPETITIVE, messages.getString(
                MsgKeys.EXERCISE_TYPE_PREFIX + ExerciseType.REPETITIVE.toString().toLowerCase()));
        exerciseTypes.put(ExerciseType.INTERVAL, messages.getString(
                MsgKeys.EXERCISE_TYPE_PREFIX + ExerciseType.INTERVAL.toString().toLowerCase()));
        return exerciseTypes;
    }

    @ModelAttribute(TIME_UNITS)
    public Map<TimeUnit, String> getTimeUnits() {
        LinkedHashMap<TimeUnit, String> timeUnits = new LinkedHashMap<>();
        timeUnits.put(TimeUnit.SECONDS, messages.getString(
                MsgKeys.TIME_UNIT_PREFIX + TimeUnit.SECONDS.toString().toLowerCase()));
        timeUnits.put(TimeUnit.MINUTES, messages.getString(
                MsgKeys.TIME_UNIT_PREFIX + TimeUnit.MINUTES.toString().toLowerCase()));
        return timeUnits;
    }

    @ExceptionHandler(UnsupportedExerciseTypeException.class)
    public String handleUnsupportedExerciseTypeException(UnsupportedExerciseTypeException e) {
        LOGGER.error(e.getMessage(), e);
        return Views.ERROR;
    }
}
