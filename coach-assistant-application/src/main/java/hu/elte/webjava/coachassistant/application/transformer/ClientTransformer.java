package hu.elte.webjava.coachassistant.application.transformer;

import hu.elte.webjava.coachassistant.application.webdomain.ClientDTO;
import hu.elte.webjava.coachassistant.application.webdomain.SubscribedClientDTO;
import hu.elte.webjava.coachassistant.domain.CalorieDiary;

import hu.elte.webjava.coachassistant.domain.Client;
import hu.elte.webjava.coachassistant.domain.Diary;
import hu.elte.webjava.coachassistant.domain.WeightDiary;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientTransformer {

    public void transform(Client client, ClientDTO dto) {
        dto.setId(client.getId());
        dto.setFirstName(client.getFirstName());
        dto.setLastName(client.getLastName());
        dto.setBirth(client.getBirth());
        dto.setGender(client.getGender());
        dto.setEmail(client.getEmail());
        dto.setHeight(client.getHeight());
    }

    public void transform(ClientDTO dto, Client client) {
        client.setId(dto.getId());
        client.setFirstName(dto.getFirstName());
        client.setLastName(dto.getLastName());
        client.setBirth(dto.getBirth());
        client.setGender(dto.getGender());
        client.setEmail(dto.getEmail());
        client.setHeight(dto.getHeight());
    }

    public List<SubscribedClientDTO> transformToSubscribedClientDTOList(List<Client> clientList) {
        return clientList.stream().map(client -> {
            SubscribedClientDTO dto = new SubscribedClientDTO();
            this.transform(client, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    public void transform(Client client, SubscribedClientDTO dto) {
        dto.setId(client.getId());
        dto.setFirstName(client.getFirstName());
        dto.setLastName(client.getLastName());
        dto.setHeight(client.getHeight());
        dto.setGender(client.getGender());

        if (client.getBirth() != null) {
            int age = Period.between(client.getBirth(), LocalDate.now()).getYears();
            dto.setAge(age);
        }

        if (client.getSubscription() != null) {
            dto.setSubscribedTrainingPlanName(client.getSubscription().getTrainingPlan().getName());
        }

        Double lastWeight = client.getDiaries().stream()
                .filter(WeightDiary.class::isInstance)
                .max(Comparator.comparing(Diary::getCreateDate))
                .map(d -> ((WeightDiary)d).getWeight())
                .orElse(null);
        dto.setLastWeight(lastWeight);

        Integer lastCalorie = client.getDiaries().stream()
                .filter(CalorieDiary.class::isInstance)
                .max(Comparator.comparing(Diary::getCreateDate))
                .map(d -> ((CalorieDiary)d).getCalories())
                .orElse(null);
        dto.setLastCalorie(lastCalorie);
    }
}
