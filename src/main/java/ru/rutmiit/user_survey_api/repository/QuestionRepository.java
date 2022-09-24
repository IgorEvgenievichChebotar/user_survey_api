package ru.rutmiit.user_survey_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rutmiit.user_survey_api.model.Question;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findByTextContainsIgnoreCase(String text);
}
