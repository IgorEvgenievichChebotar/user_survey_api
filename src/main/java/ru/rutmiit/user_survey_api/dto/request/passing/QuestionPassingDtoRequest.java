package ru.rutmiit.user_survey_api.dto.request.passing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A DTO for the {@link ru.rutmiit.user_survey_api.model.Question} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuestionPassingDtoRequest {
    private Long id;
}