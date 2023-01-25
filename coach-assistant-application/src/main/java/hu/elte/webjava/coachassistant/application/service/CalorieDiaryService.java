package hu.elte.webjava.coachassistant.application.service;

import hu.elte.webjava.coachassistant.domain.CalorieDiary;

import java.util.List;

public interface CalorieDiaryService {
    List<CalorieDiary> getDiariesByClientId(int clientId);

    List<CalorieDiary> getDiariesByClientId(int clientId, int limit);

    CalorieDiary getDiary(int diaryId);

    CalorieDiary createDiary(int clientId, CalorieDiary diary);

    CalorieDiary updateDiary(CalorieDiary diary);

    void deleteDiary(int diaryId);
}