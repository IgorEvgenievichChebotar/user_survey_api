package ru.rutmiit.user_survey_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rutmiit.user_survey_api.model.Survey;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
}
