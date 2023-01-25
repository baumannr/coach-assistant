package hu.elte.webjava.coachassistant.application.transformer;

import hu.elte.webjava.coachassistant.application.webdomain.CalorieDiaryDTO;
import hu.elte.webjava.coachassistant.application.webdomain.WeightDiaryDTO;
import hu.elte.webjava.coachassistant.domain.CalorieDiary;
import hu.elte.webjava.coachassistant.domain.WeightDiary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WeightDiaryTransformer {
    public void transform(WeightDiary weightDiary, WeightDiaryDTO dto) {
        dto.setId(weightDiary.getId());
        dto.setCreateDate(weightDiary.getCreateDate());
        dto.setWeight(weightDiary.getWeight());
    }

    public void transform(WeightDiaryDTO dto, WeightDiary weightDiary) {
        weightDiary.setId(dto.getId());
        weightDiary.setCreateDate(dto.getCreateDate());
        weightDiary.setWeight(dto.getWeight());
    }

    public List<WeightDiaryDTO> transformToWeightDiaryDTOList(List<WeightDiary> weightDiaryList) {
        return weightDiaryList.stream().map(diary -> {
            WeightDiaryDTO dto = new WeightDiaryDTO();
            this.transform(diary, dto);
            return dto;
        }).collect(Collectors.toList());
    }
}
