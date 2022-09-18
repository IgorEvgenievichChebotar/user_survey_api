package ru.rutmiit.user_survey_api.dto.request.creating;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.rutmiit.user_survey_api.model.enumeration.QuestionType;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * A DTO for the {@link ru.rutmiit.user_survey_api.model.Question} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuestionDtoCreatingRequest {
    private String text;
    private Set<OptionDtoCreatingRequest> options = new LinkedHashSet<>();
    private QuestionType type;
}