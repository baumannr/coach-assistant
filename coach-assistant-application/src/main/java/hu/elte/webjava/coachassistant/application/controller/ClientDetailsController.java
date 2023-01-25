package hu.elte.webjava.coachassistant.application.controller;

import hu.elte.webjava.coachassistant.application.common.Mappings;
import hu.elte.webjava.coachassistant.application.common.Views;
import hu.elte.webjava.coachassistant.application.exception.EntityNotFoundException;
import hu.elte.webjava.coachassistant.application.service.CalorieDiaryService;
import hu.elte.webjava.coachassistant.application.service.ClientService;
import hu.elte.webjava.coachassistant.application.service.WeightDiaryService;
import hu.elte.webjava.coachassistant.application.transformer.CalorieDiaryTransformer;
import hu.elte.webjava.coachassistant.application.transformer.ClientTransformer;
import hu.elte.webjava.coachassistant.application.transformer.WeightDiaryTransformer;
import hu.elte.webjava.coachassistant.application.webdomain.CalorieDiaryDTO;
import hu.elte.webjava.coachassistant.application.webdomain.SubscribedClientDTO;
import hu.elte.webjava.coachassistant.application.webdomain.WeightDiaryDTO;
import hu.elte.webjava.coachassistant.domain.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ClientDetailsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientDetailsController.class);
    private static final String CALORIE_DIARY_DTO_LIST = "calorieDiaryDTOList";
    private static final String WEIGHT_DIARY_DTO_LIST = "weightDiaryDTOList";
    private static final String CLIENT_DTO = "subscribedClientDTO";
    private final ClientService clientService;
    private final CalorieDiaryService calorieDiaryService;
    private final WeightDiaryService weightDiaryService;
    private final ClientTransformer transformer;
    private final CalorieDiaryTransformer calorieDiaryTransformer;
    private final WeightDiaryTransformer weightDiaryTransformer;

    public ClientDetailsController(ClientService clientService,
                                   CalorieDiaryService calorieDiaryService,
                                   WeightDiaryService weightDiaryService,
                                   ClientTransformer transformer,
                                   CalorieDiaryTransformer calorieDiaryTransformer,
                                   WeightDiaryTransformer weightDiaryTransformer) {
        this.clientService = clientService;
        this.calorieDiaryService = calorieDiaryService;
        this.weightDiaryService = weightDiaryService;
        this.transformer = transformer;
        this.calorieDiaryTransformer = calorieDiaryTransformer;
        this.weightDiaryTransformer = weightDiaryTransformer;
    }

    @GetMapping(Mappings.GET_CLIENT_DETAILS_INDEX)
    public String index(Model model, @PathVariable("id") int clientId) {
        Client client = clientService.getClient(clientId);
        if (client == null) {
            LOGGER.error("Client not found with id: {}", clientId);
            throw new EntityNotFoundException();
        }

        SubscribedClientDTO subscribedClientDTO = new SubscribedClientDTO();
        transformer.transform(client, subscribedClientDTO);

        List<CalorieDiaryDTO> calorieDiaryDTOList = calorieDiaryTransformer
                .transformToCalorieDiaryDTOList(calorieDiaryService.getDiariesByClientId(clientId, 14));

        List<WeightDiaryDTO> weightDiaryDTOList = weightDiaryTransformer
                .transformToWeightDiaryDTOList(weightDiaryService.getDiariesByClientId(clientId, 14));

        model.addAttribute(CLIENT_DTO, subscribedClientDTO);
        model.addAttribute(CALORIE_DIARY_DTO_LIST, calorieDiaryDTOList);
        model.addAttribute(WEIGHT_DIARY_DTO_LIST, weightDiaryDTOList);

        return Views.CLIENT_DETAILS_INDEX;
    }
}
