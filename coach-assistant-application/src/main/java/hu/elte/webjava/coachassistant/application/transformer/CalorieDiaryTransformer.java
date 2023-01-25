package hu.elte.webjava.coachassistant.application.transformer;

import hu.elte.webjava.coachassistant.application.webdomain.CalorieDiaryDTO;
import hu.elte.webjava.coachassistant.domain.CalorieDiary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CalorieDiaryTransformer {
    public void transform(CalorieDiary calorieDiary, CalorieDiaryDTO dto) {
        dto.setId(calorieDiary.getId());
        dto.setCreateDate(calorieDiary.getCreateDate());
        dto.setCalories(calorieDiary.getCalories());
    }

    public void transform(CalorieDiaryDTO dto, CalorieDiary calorieDiary) {
        calorieDiary.setId(dto.getId());
        calorieDiary.setCreateDate(dto.getCreateDate());
        calorieDiary.setCalories(dto.getCalories());
    }

    public List<CalorieDiaryDTO> transformToCalorieDiaryDTOList(List<CalorieDiary> calorieDiaryList) {
        return calorieDiaryList.stream().map(diary -> {
            CalorieDiaryDTO dto = new CalorieDiaryDTO();
            this.transform(diary, dto);
            return dto;
        }).collect(Collectors.toList());
    }
}
