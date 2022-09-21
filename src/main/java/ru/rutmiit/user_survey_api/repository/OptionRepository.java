package ru.rutmiit.user_survey_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rutmiit.user_survey_api.model.Option;

public interface OptionRepository extends JpaRepository<Option, Long> {
}
