package ru.rutmiit.user_survey_api.service;

import ru.rutmiit.user_survey_api.model.Answer;

import java.util.List;

public interface AnswerService {
    void commitAll(List<Answer> answers);

    List<Answer> findByUserId(Long id);
}
