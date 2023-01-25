package hu.elte.webjava.coachassistant.application.service;

import hu.elte.webjava.coachassistant.domain.WeightDiary;

import java.util.List;

public interface WeightDiaryService {
    List<WeightDiary> getDiariesByClientId(int clientId);
    List<WeightDiary> getDiariesByClientId(int clientId, int limit);

    WeightDiary getDiary(int diaryId);

    WeightDiary createDiary(int clientId,WeightDiary diary);

    WeightDiary updateDiary(WeightDiary diary);

    void deleteDiary(int diaryId);
}
