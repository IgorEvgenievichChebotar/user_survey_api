package ru.rutmiit.user_survey_api.service;

import ru.rutmiit.user_survey_api.model.Survey;

import java.util.List;

public interface SurveyService {

    Survey findById(Long id);

    List<Survey> findActive();

    List<Survey> findPassedByUserId(Long id); // only for current user(id)

    void pass(Survey survey, Long id);

    //-------------------------------------------------------------------//

    Survey create(Survey survey);

    Survey update(Survey survey, Long id);

    void deleteById(Long id);
}
