package ru.rutmiit.user_survey_api.service;

import ru.rutmiit.user_survey_api.model.Question;

public interface QuestionService {

    //-------------------------------------------------------------------//

    Question create(Question question, Long surveyId);

    Question update(Question question, Long id);

    void deleteById(Long id);
}
