package hu.elte.webjava.coachassistant.application.controller;

import hu.elte.webjava.coachassistant.application.common.*;
import hu.elte.webjava.coachassistant.application.exception.EntityNotFoundException;
import hu.elte.webjava.coachassistant.application.security.SecurityHelper;
import hu.elte.webjava.coachassistant.application.service.CoachService;
import hu.elte.webjava.coachassistant.application.service.UserService;
import hu.elte.webjava.coachassistant.application.transformer.CoachTransformer;
import hu.elte.webjava.coachassistant.application.webdomain.CoachDTO;
import hu.elte.webjava.coachassistant.domain.Coach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class CoachProfileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoachProfileController.class);
    private static final String COACH_DTO = "coachDTO";
    private final CoachService coachService;
    private final CoachTransformer transformer;
    private final MessagesBundle messages;
    private final UserService userService;

    @Autowired
    public CoachProfileController(CoachService coachService,
                                  CoachTransformer transformer,
                                  MessagesBundle messages,
                                  UserService userService) {
        this.coachService = coachService;
        this.transformer = transformer;
        this.messages = messages;
        this.userService = userService;
    }

    @GetMapping(Mappings.GET_COACH_PROFILE_INDEX)
    public String index(Model model) {
        Coach coach = coachService.getCoach(SecurityHelper.getUserId());
        CoachDTO coachDTO = new CoachDTO();
        transformer.transform(coach, coachDTO);
        model.addAttribute(COACH_DTO, coachDTO);
        return Views.COACH_PROFILE_INDEX;
    }

    @GetMapping(Mappings.GET_COACH_PROFILE_EDIT)
    public String edit(Model model) {
        Coach coach = coachService.getCoach(SecurityHelper.getUserId());
        if (coach == null) {
            throw new EntityNotFoundException();
        }

        CoachDTO coachDTO = new CoachDTO();
        transformer.transform(coach, coachDTO);
        model.addAttribute(COACH_DTO, coachDTO);

        return Views.COACH_PROFILE_EDIT;
    }

    @PostMapping(Mappings.POST_COACH_PROFILE_EDIT)
    public String edit(@Valid CoachDTO coachDTO, BindingResult bindingResult, Model model,
                       RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(AppConst.MESSAGE_WARNING, messages.getString(MsgKeys.COACH_PROFILE_EDIT_WARN));
            return Views.COACH_PROFILE_EDIT;
        }

        Coach coach = coachService.getCoach(coachDTO.getId());
        transformer.transform(coachDTO, coach);
        userService.update(coach);

        LOGGER.info("Coach [id={}] successfully updated!", coach.getId());
        redirectAttributes.addFlashAttribute(AppConst.MESSAGE_SUCCESS,
                messages.getString(MsgKeys.COACH_PROFILE_EDIT_SUCCESS));

        return Views.REDIRECT_COACH_PROFILE_INDEX;
    }

}
