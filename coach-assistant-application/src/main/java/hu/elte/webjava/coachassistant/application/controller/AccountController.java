package hu.elte.webjava.coachassistant.application.controller;

import hu.elte.webjava.coachassistant.application.common.*;
import hu.elte.webjava.coachassistant.application.exception.UserAlreadyExistException;
import hu.elte.webjava.coachassistant.application.factory.UserFactory;
import hu.elte.webjava.coachassistant.application.service.UserService;
import hu.elte.webjava.coachassistant.application.webdomain.RegisterDTO;
import hu.elte.webjava.coachassistant.domain.DomainUser;
import hu.elte.webjava.coachassistant.application.webdomain.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class AccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
    private static final String REGISTER_DTO = "registerDTO";
    private static final String USER_ALREADY_EXISTS = "errorUserAlreadyExists";
    private static final String USER_TYPE = "userTypes";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String EMAIL = "email";
    private final UserService userService;
    private final UserFactory userFactory;
    private final MessagesBundle messages;

    @Autowired
    public AccountController(UserService userService, UserFactory userFactory, MessagesBundle messagesBundle) {
        this.userService = userService;
        this.userFactory = userFactory;
        this.messages = messagesBundle;
    }

    @GetMapping(Mappings.LOGIN)
    public String login() {
        return Views.GET_LOGIN;
    }

    @GetMapping(Mappings.GET_REGISTER)
    public String register(Model model) {
        RegisterDTO registerDTO = new RegisterDTO();
        model.addAttribute(REGISTER_DTO, registerDTO);
        return Views.REGISTER;
    }

    @PostMapping(Mappings.POST_REGISTER)
    public String register(@Valid RegisterDTO registerDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return Views.REGISTER;
        }
        
        DomainUser user = userFactory.getUser(registerDto);
        userService.register(user);

        LOGGER.info("Successful registration, user id: {}", user.getId());
        redirectAttributes.addFlashAttribute(AppConst.MESSAGE_SUCCESS,
                messages.getString(MsgKeys.REGISTRATION_SUCCESS));

        return Views.REDIRECT_LOGIN;
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ModelAndView handleUserAlreadyExistException(HttpServletRequest request,
                                                        UserAlreadyExistException e) {
        String errorMessage = messages.getString(MsgKeys.TEXT_ERROR_USER_ALREADY_EXISTS);

        RegisterDTO registerDto = new RegisterDTO();
        registerDto.setFirstName(request.getParameter(FIRST_NAME));
        registerDto.setLastName(request.getParameter(LAST_NAME));
        registerDto.setEmail(request.getParameter(EMAIL));
        registerDto.setUserType(UserType.valueOf(request.getParameter(USER_TYPE)));

        ModelAndView modelAndView = new ModelAndView(Views.REDIRECT_REGISTER);
        modelAndView.addObject(REGISTER_DTO, registerDto);
        modelAndView.addObject(USER_ALREADY_EXISTS, errorMessage);
        modelAndView.addObject(USER_TYPE, getUserTypes());

        return modelAndView;
    }

    @ModelAttribute(USER_TYPE)
    public Map<UserType, String> getUserTypes() {
        Map<UserType, String> userTypeMap = new LinkedHashMap<>();
        userTypeMap.put(UserType.CLIENT, messages.getString(
                MsgKeys.USER_TYPE_PREFIX + UserType.CLIENT.toString().toLowerCase()));
        userTypeMap.put(UserType.COACH, messages.getString(
                MsgKeys.USER_TYPE_PREFIX + UserType.COACH.toString().toLowerCase()));
        return userTypeMap;
    }
}
