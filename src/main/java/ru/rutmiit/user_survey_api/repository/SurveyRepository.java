package ru.rutmiit.user_survey_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.rutmiit.user_survey_api.model.Survey;

import java.util.List;
import java.util.Optional;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
    @Query("select distinct a.survey " +
            "from Answer a " +
            "where a.usr.id = ?1")
    List<Survey> findPassedSurveysByUserId(Long id);

    Optional<Survey> findByTitleIgnoreCase(String title);

    boolean existsByTitleIgnoreCase(String title);
}
