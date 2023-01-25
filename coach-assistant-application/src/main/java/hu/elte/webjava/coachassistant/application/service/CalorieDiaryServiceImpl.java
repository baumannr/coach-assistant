package hu.elte.webjava.coachassistant.application.service;

import hu.elte.webjava.coachassistant.domain.CalorieDiary;
import hu.elte.webjava.coachassistant.domain.Client;
import hu.elte.webjava.coachassistant.repository.CalorieDiaryRepository;
import hu.elte.webjava.coachassistant.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CalorieDiaryServiceImpl implements CalorieDiaryService {

    private final CalorieDiaryRepository calorieDiaryRepository;

    private final ClientRepository clientRepository;

    @Autowired
    public CalorieDiaryServiceImpl(CalorieDiaryRepository calorieDiaryRepository, ClientRepository clientRepository) {
        this.calorieDiaryRepository = calorieDiaryRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public List<CalorieDiary> getDiariesByClientId(int clientId) {
        return calorieDiaryRepository.findByClientIdOrderByCreateDateDesc(clientId);
    }

    @Override
    public List<CalorieDiary> getDiariesByClientId(int clientId, int limit) {
        List<CalorieDiary> calorieDiaries = calorieDiaryRepository
                .findByClientIdOrderByCreateDateDesc(clientId, PageRequest.of(0, limit));
        Collections.reverse(calorieDiaries);
        return calorieDiaries;
    }

    @Override
    public CalorieDiary getDiary(int diaryId) {
        return calorieDiaryRepository.findById(diaryId).orElse(null);
    }

    @Override
    public CalorieDiary createDiary(int clientId, CalorieDiary diary) {
        Client client = clientRepository.findById(clientId).orElse(null);
        diary.setClient(client);
        return calorieDiaryRepository.save(diary);
    }

    @Override
    public CalorieDiary updateDiary(CalorieDiary diary) {
        return calorieDiaryRepository.save(diary);
    }

    @Override
    public void deleteDiary(int diaryId) {
        calorieDiaryRepository.deleteById(diaryId);
    }
}