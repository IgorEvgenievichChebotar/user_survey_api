package ru.rutmiit.user_survey_api.dto.request.passing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A DTO for the {@link ru.rutmiit.user_survey_api.model.Answer} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnswerDtoRequest {
    private Long questionId;
    private String answer;
}