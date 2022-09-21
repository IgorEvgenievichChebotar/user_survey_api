package ru.rutmiit.user_survey_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.rutmiit.user_survey_api.model.Survey;

import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
    @Query("select us.survey from UsrSurvey us where us.usr.id=?1")
    List<Survey> findPassedSurveysByUserId(Long id);
}
