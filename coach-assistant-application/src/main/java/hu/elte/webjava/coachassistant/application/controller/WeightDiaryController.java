package hu.elte.webjava.coachassistant.application.controller;

import hu.elte.webjava.coachassistant.application.common.*;
import hu.elte.webjava.coachassistant.application.exception.EntityNotFoundException;
import hu.elte.webjava.coachassistant.application.security.SecurityHelper;
import hu.elte.webjava.coachassistant.application.service.WeightDiaryService;
import hu.elte.webjava.coachassistant.application.transformer.WeightDiaryTransformer;
import hu.elte.webjava.coachassistant.application.webdomain.WeightDiaryDTO;
import hu.elte.webjava.coachassistant.domain.WeightDiary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class WeightDiaryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeightDiaryController.class);
    private static final String WEIGHT_DIARY_DTO = "weightDiaryDTO";
    private static final String WEIGHT_DIARY_PAGED_LIST_HOLDER = "weightDiaryPagedListHolder";
    private static final int PAGE_SIZE = 5;
    private final WeightDiaryService weightDiaryService;
    private final WeightDiaryTransformer weightDiaryTransformer;
    private final MessagesBundle messages;

    @Autowired
    public WeightDiaryController(WeightDiaryService weightDiaryService,
                                 WeightDiaryTransformer weightDiaryTransformer,
                                 MessagesBundle messages) {
        this.weightDiaryService = weightDiaryService;
        this.weightDiaryTransformer = weightDiaryTransformer;
        this.messages = messages;
    }

    @GetMapping(Mappings.GET_WEIGHT_DIARY_INDEX)
    public String index(Model model, @RequestParam Optional<Integer> page) {
        int clientId = SecurityHelper.getUserId();
        List<WeightDiaryDTO> weightDiaryDTOList = weightDiaryTransformer.transformToWeightDiaryDTOList(
                weightDiaryService.getDiariesByClientId(clientId));

        PagedListHolder<WeightDiaryDTO> weightDiaryPagedListHolder = new PagedListHolder<>(weightDiaryDTOList);
        weightDiaryPagedListHolder.setPage(page.orElse(0));
        weightDiaryPagedListHolder.setPageSize(PAGE_SIZE);

        model.addAttribute(WEIGHT_DIARY_PAGED_LIST_HOLDER, weightDiaryPagedListHolder);
        return Views.WEIGHT_DIARY_INDEX;
    }

    @GetMapping(Mappings.GET_WEIGHT_DIARY_CREATE)
    public String create(Model model) {
        WeightDiaryDTO weightDiaryDTO = new WeightDiaryDTO();
        model.addAttribute(WEIGHT_DIARY_DTO, weightDiaryDTO);
        return Views.WEIGHT_DIARY_CREATE;
    }

    @PostMapping(Mappings.POST_WEIGHT_DIARY_CREATE)
    public String create(@Valid WeightDiaryDTO weightDiaryDTO,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(AppConst.MESSAGE_WARNING, messages.getString(MsgKeys.WEIGHT_DIARY_CREATE_WARN));
            return Views.WEIGHT_DIARY_CREATE;
        }

        WeightDiary weightDiary = new WeightDiary();
        weightDiaryTransformer.transform(weightDiaryDTO, weightDiary);
        weightDiaryService.createDiary(SecurityHelper.getUserId(), weightDiary);

        LOGGER.info("Weight diary [id={}] successfully created!", weightDiary.getId());
        redirectAttributes.addFlashAttribute(AppConst.MESSAGE_SUCCESS,
                messages.getString(MsgKeys.WEIGHT_DIARY_CREATE_SUCCESS));

        return Views.REDIRECT_WEIGHT_DIARY_INDEX;
    }

    @GetMapping(Mappings.GET_WEIGHT_DIARY_EDIT)
    public String edit(Model model, @PathVariable("id") int diaryId) {
        WeightDiary weightDiary = weightDiaryService.getDiary(diaryId);
        if (weightDiary == null) {
            LOGGER.error("No weight diary found with id: {}", diaryId);
            throw new EntityNotFoundException();
        }

        WeightDiaryDTO weightDiaryDTO = new WeightDiaryDTO();
        weightDiaryTransformer.transform(weightDiary, weightDiaryDTO);
        model.addAttribute(WEIGHT_DIARY_DTO, weightDiaryDTO);

        return Views.WEIGHT_DIARY_EDIT;
    }

    @PostMapping(Mappings.POST_WEIGHT_DIARY_EDIT)
    public String edit(@Valid WeightDiaryDTO weightDiaryDTO,
                       BindingResult bindingResult,
                       Model model,
                       RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(AppConst.MESSAGE_WARNING, messages.getString(MsgKeys.WEIGHT_DIARY_EDIT_WARN));
            return Views.WEIGHT_DIARY_EDIT;
        }

        WeightDiary weightDiary = weightDiaryService.getDiary(weightDiaryDTO.getId());
        weightDiaryTransformer.transform(weightDiaryDTO, weightDiary);
        weightDiaryService.updateDiary(weightDiary);

        LOGGER.info("Weight Diary [id={}] successfully updated!", weightDiary.getId());
        redirectAttributes.addFlashAttribute(AppConst.MESSAGE_SUCCESS,
                messages.getString(MsgKeys.WEIGHT_DIARY_EDIT_SUCCESS));

        return Views.REDIRECT_WEIGHT_DIARY_INDEX;
    }

    @PostMapping(Mappings.POST_WEIGHT_DIARY_DELETE)
    public String delete(@PathVariable("id") int diaryId, RedirectAttributes redirectAttributes) {
        weightDiaryService.deleteDiary(diaryId);

        LOGGER.info("Weight Diary [id={}] successfully deleted!", diaryId);
        redirectAttributes.addFlashAttribute(AppConst.MESSAGE_SUCCESS,
                messages.getString(MsgKeys.WEIGHT_DIARY_DELETE_SUCCESS));

        return Views.REDIRECT_WEIGHT_DIARY_INDEX;
    }
}
