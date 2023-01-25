package hu.elte.webjava.coachassistant.application.transformer;

import hu.elte.webjava.coachassistant.application.webdomain.TrainingPlanDTO;
import hu.elte.webjava.coachassistant.domain.TrainingPlan;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrainingPlanTransformer {

    public void transform(TrainingPlan trainingPlan, TrainingPlanDTO dto) {
        dto.setId(trainingPlan.getId());
        dto.setName(trainingPlan.getName());
        dto.setTrainingType(trainingPlan.getTrainingType());
        dto.setLength(trainingPlan.getLength());
        dto.setCalorieBurn(trainingPlan.getCalorieBurn());
        dto.setRecommendation(trainingPlan.getRecommendation());
    }

    public void transform(TrainingPlanDTO dto, TrainingPlan trainingPlan) {
        trainingPlan.setId(dto.getId());
        trainingPlan.setName(dto.getName());
        trainingPlan.setTrainingType(dto.getTrainingType());
        trainingPlan.setLength(dto.getLength());
        trainingPlan.setCalorieBurn(dto.getCalorieBurn());
        trainingPlan.setRecommendation(dto.getRecommendation());
    }

    public List<TrainingPlanDTO> transformToTrainingPlanDTOList(List<TrainingPlan> trainingPlanList) {
        return trainingPlanList.stream().map(trainingPlan -> {
            TrainingPlanDTO dto = new TrainingPlanDTO();
            this.transform(trainingPlan, dto);
            return dto;
        }).collect(Collectors.toList());
    }
}
