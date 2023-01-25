package hu.elte.webjava.coachassistant.application.service;

import hu.elte.webjava.coachassistant.domain.Client;
import hu.elte.webjava.coachassistant.domain.WeightDiary;
import hu.elte.webjava.coachassistant.repository.ClientRepository;
import hu.elte.webjava.coachassistant.repository.WeightDiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class WeightDiaryServiceImpl implements  WeightDiaryService {

    private final WeightDiaryRepository weightDiaryRepository;

    private final ClientRepository clientRepository;

    @Autowired
    public WeightDiaryServiceImpl(WeightDiaryRepository weightDiaryRepository, ClientRepository clientRepository) {
        this.weightDiaryRepository = weightDiaryRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public List<WeightDiary> getDiariesByClientId(int clientId) {
        return weightDiaryRepository.findByClientIdOrderByCreateDateDesc(clientId);
    }

    @Override
    public List<WeightDiary> getDiariesByClientId(int clientId, int limit) {
        List<WeightDiary> weightDiaries = weightDiaryRepository
                .findByClientIdOrderByCreateDateDesc(clientId, PageRequest.of(0, limit));
        Collections.reverse(weightDiaries);
        return weightDiaries;
    }

    @Override
    public WeightDiary getDiary(int diaryId) {
        return weightDiaryRepository.findById(diaryId).orElse(null);
    }

    @Override
    public WeightDiary createDiary(int clientId, WeightDiary diary) {
        Client client = clientRepository.findById(clientId).orElse(null);
        diary.setClient(client);
        return weightDiaryRepository.save(diary);
    }

    @Override
    public WeightDiary updateDiary(WeightDiary diary) {
        return weightDiaryRepository.save(diary);
    }

    @Override
    public void deleteDiary(int diaryId) {
        weightDiaryRepository.deleteById(diaryId);
    }
}
