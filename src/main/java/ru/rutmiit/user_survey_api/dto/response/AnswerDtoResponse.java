package ru.rutmiit.user_survey_api.dto.response;

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
public class AnswerDtoResponse {
    private String answer;
}