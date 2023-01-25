package hu.elte.webjava.coachassistant.application.controller;

import hu.elte.webjava.coachassistant.application.common.*;
import hu.elte.webjava.coachassistant.application.exception.EntityNotFoundException;
import hu.elte.webjava.coachassistant.application.security.SecurityHelper;
import hu.elte.webjava.coachassistant.application.service.ClientService;
import hu.elte.webjava.coachassistant.application.service.UserService;
import hu.elte.webjava.coachassistant.application.transformer.ClientTransformer;
import hu.elte.webjava.coachassistant.application.webdomain.ClientDTO;
import hu.elte.webjava.coachassistant.domain.Client;
import hu.elte.webjava.coachassistant.domain.Gender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class ClientProfileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientProfileController.class);
    private static final String CLIENT_DTO = "clientDTO";
    private static final String GENDERS = "genders";
    private final ClientService clientService;
    private final ClientTransformer transformer;
    private final MessagesBundle messages;
    private final UserService userService;

    @Autowired
    public ClientProfileController(ClientService clientService,
                                   ClientTransformer transformer,
                                   MessagesBundle messages,
                                   UserService userService) {
        this.clientService = clientService;
        this.transformer = transformer;
        this.messages = messages;
        this.userService = userService;
    }

    @GetMapping(Mappings.GET_CLIENT_PROFILE_INDEX)
    public String index(Model model) {
        Client client = clientService.getClient(SecurityHelper.getUserId());
        ClientDTO clientDTO = new ClientDTO();
        transformer.transform(client, clientDTO);
        model.addAttribute(CLIENT_DTO, clientDTO);
        return Views.CLIENT_PROFILE_INDEX;
    }

    @GetMapping(Mappings.GET_CLIENT_PROFILE_EDIT)
    public String edit(Model model) {
        Client client = clientService.getClient(SecurityHelper.getUserId());
        if (client == null) {
            throw new EntityNotFoundException();
        }

        ClientDTO clientDTO = new ClientDTO();
        transformer.transform(client, clientDTO);
        model.addAttribute(CLIENT_DTO, clientDTO);

        return Views.CLIENT_PROFILE_EDIT;
    }

    @PostMapping(Mappings.POST_CLIENT_PROFILE_EDIT)
    public String edit(@Valid ClientDTO clientDTO, BindingResult bindingResult, Model model,
                       RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(AppConst.MESSAGE_WARNING, messages.getString(MsgKeys.CLIENT_PROFILE_EDIT_WARN));
            return Views.CLIENT_PROFILE_EDIT;
        }

        Client client = clientService.getClient(clientDTO.getId());
        transformer.transform(clientDTO, client);
        userService.update(client);

        LOGGER.info("Client [id={}] successfully updated!", client.getId());

        redirectAttributes.addFlashAttribute(AppConst.MESSAGE_SUCCESS,
                messages.getString(MsgKeys.CLIENT_PROFILE_EDIT_SUCCESS));

        return Views.REDIRECT_CLIENT_PROFILE_INDEX;
    }

    @ModelAttribute(GENDERS)
    public Map<Gender, String> getUserTypes() {
        Map<Gender, String> genderTypeMap = new LinkedHashMap<>();
        genderTypeMap.put(Gender.FEMALE, messages.getString(
                MsgKeys.GENDER_PREFIX + Gender.FEMALE.toString().toLowerCase()));
        genderTypeMap.put(Gender.MALE, messages.getString(
                MsgKeys.GENDER_PREFIX + Gender.MALE.toString().toLowerCase()));
        return genderTypeMap;
    }
}
