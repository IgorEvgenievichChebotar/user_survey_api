package ru.rutmiit.user_survey_api.service;

import ru.rutmiit.user_survey_api.model.Question;

import java.util.List;

public interface QuestionService {
    Question findById(Long id);

    Question findByText(String text);

    List<Question> findAllBySurveyId(Long id);

    boolean existsByText(String text);

    //-------------------------------------------------------------------//

    Question create(Question question, Long surveyId);
    List<Question> createAll(List<Question> questions, Long surveyId);

    Question update(Question question, Long id);

    void deleteById(Long id);
}
