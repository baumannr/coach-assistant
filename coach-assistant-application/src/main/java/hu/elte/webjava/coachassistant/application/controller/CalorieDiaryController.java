package hu.elte.webjava.coachassistant.application.controller;

import hu.elte.webjava.coachassistant.application.common.*;
import hu.elte.webjava.coachassistant.application.exception.EntityNotFoundException;
import hu.elte.webjava.coachassistant.application.security.SecurityHelper;
import hu.elte.webjava.coachassistant.application.service.CalorieDiaryService;
import hu.elte.webjava.coachassistant.application.transformer.CalorieDiaryTransformer;
import hu.elte.webjava.coachassistant.application.webdomain.CalorieDiaryDTO;
import hu.elte.webjava.coachassistant.domain.CalorieDiary;
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
public class CalorieDiaryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalorieDiaryController.class);
    private static final String CALORIE_DIARY_DTO = "calorieDiaryDTO";
    private static final String CALORIE_DIARY_PAGED_LIST_HOLDER = "calorieDiaryPagedListHolder";
    private static final int PAGE_SIZE = 5;
    private final CalorieDiaryService calorieDiaryService;
    private final CalorieDiaryTransformer calorieDiaryTransformer;
    private final MessagesBundle messages;

    @Autowired
    public CalorieDiaryController(CalorieDiaryService calorieDiaryService,
                                  CalorieDiaryTransformer calorieDiaryTransformer,
                                  MessagesBundle messages) {
        this.calorieDiaryService = calorieDiaryService;
        this.calorieDiaryTransformer = calorieDiaryTransformer;
        this.messages = messages;
    }

    @GetMapping(Mappings.GET_CALORIE_DIARY_INDEX)
    public String index(Model model, @RequestParam Optional<Integer> page) {
        int clientId = SecurityHelper.getUserId();
        List<CalorieDiaryDTO> calorieDiaryDTOList = calorieDiaryTransformer.transformToCalorieDiaryDTOList(
                calorieDiaryService.getDiariesByClientId(clientId));

        PagedListHolder<CalorieDiaryDTO> calorieDiaryPagedListHolder = new PagedListHolder<>(calorieDiaryDTOList);
        calorieDiaryPagedListHolder.setPage(page.orElse(0));
        calorieDiaryPagedListHolder.setPageSize(PAGE_SIZE);

        model.addAttribute(CALORIE_DIARY_PAGED_LIST_HOLDER, calorieDiaryPagedListHolder);
        return Views.CALORIE_DIARY_INDEX;
    }

    @GetMapping(Mappings.GET_CALORIE_DIARY_CREATE)
    public String create(Model model) {
        CalorieDiaryDTO calorieDiaryDTO = new CalorieDiaryDTO();
        model.addAttribute(CALORIE_DIARY_DTO, calorieDiaryDTO);
        return Views.CALORIE_DIARY_CREATE;
    }

    @PostMapping(Mappings.POST_CALORIE_DIARY_CREATE)
    public String create(@Valid CalorieDiaryDTO calorieDiaryDTO,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(AppConst.MESSAGE_WARNING, messages.getString(MsgKeys.CALORIE_DIARY_CREATE_WARN));
            return Views.CALORIE_DIARY_CREATE;
        }

        CalorieDiary calorieDiary = new CalorieDiary();
        calorieDiaryTransformer.transform(calorieDiaryDTO, calorieDiary);
        calorieDiaryService.createDiary(SecurityHelper.getUserId(), calorieDiary);

        LOGGER.info("Calorie diary [id={}] successfully created!", calorieDiary.getId());
        redirectAttributes.addFlashAttribute(AppConst.MESSAGE_SUCCESS,
                messages.getString(MsgKeys.CALORIE_DIARY_CREATE_SUCCESS));

        return Views.REDIRECT_CALORIE_DIARY_INDEX;
    }

    @GetMapping(Mappings.GET_CALORIE_DIARY_EDIT)
    public String edit(Model model, @PathVariable("id") int diaryId) {
        CalorieDiary calorieDiary = calorieDiaryService.getDiary(diaryId);
        if (calorieDiary == null) {
            LOGGER.error("No calorie diary found with id: {}", diaryId);
            throw new EntityNotFoundException();
        }

        CalorieDiaryDTO calorieDiaryDTO = new CalorieDiaryDTO();
        calorieDiaryTransformer.transform(calorieDiary, calorieDiaryDTO);
        model.addAttribute(CALORIE_DIARY_DTO, calorieDiaryDTO);

        return Views.CALORIE_DIARY_EDIT;
    }

    @PostMapping(Mappings.POST_CALORIE_DIARY_EDIT)
    public String edit(@Valid CalorieDiaryDTO calorieDiaryDTO,
                       BindingResult bindingResult,
                       Model model,
                       RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(AppConst.MESSAGE_WARNING, messages.getString(MsgKeys.CALORIE_DIARY_EDIT_WARN));
            return Views.CALORIE_DIARY_EDIT;
        }

        CalorieDiary calorieDiary = calorieDiaryService.getDiary(calorieDiaryDTO.getId());
        calorieDiaryTransformer.transform(calorieDiaryDTO, calorieDiary);
        calorieDiaryService.updateDiary(calorieDiary);

        LOGGER.info("Calorie Diary [id={}] successfully updated!", calorieDiary.getId());
        redirectAttributes.addFlashAttribute(AppConst.MESSAGE_SUCCESS,
                messages.getString(MsgKeys.CALORIE_DIARY_EDIT_SUCCESS));

        return Views.REDIRECT_CALORIE_DIARY_INDEX;
    }

    @PostMapping(Mappings.POST_CALORIE_DIARY_DELETE)
    public String delete(@PathVariable("id") int diaryId, RedirectAttributes redirectAttributes) {
        calorieDiaryService.deleteDiary(diaryId);

        LOGGER.info("Calorie Diary [id={}] successfully deleted!", diaryId);
        redirectAttributes.addFlashAttribute(AppConst.MESSAGE_SUCCESS,
                messages.getString(MsgKeys.CALORIE_DIARY_DELETE_SUCCESS));

        return Views.REDIRECT_CALORIE_DIARY_INDEX;
    }
}
