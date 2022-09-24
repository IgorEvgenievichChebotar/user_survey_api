package ru.rutmiit.user_survey_api.service;

import ru.rutmiit.user_survey_api.model.Answer;
import ru.rutmiit.user_survey_api.model.Survey;
import ru.rutmiit.user_survey_api.model.Usr;

import java.util.List;

public interface SurveyService {

    Survey findById(Long id);
    Survey findByTitle(String title);

    List<Survey> findActive();

    List<Survey> findAll();

    void pass(Long surveyId, Usr user, List<Answer> answers);

    //-------------------------------------------------------------------//

    List<Survey> findPassedSurveysByUserId(Long id); /* only for current user(id)
                                                 otherwise for admin */

    //-------------------------------------------------------------------//

    Survey create(Survey survey);

    Survey update(Survey survey, Long id);

    void deleteById(Long id);
}
