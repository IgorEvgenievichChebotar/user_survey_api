package ru.rutmiit.user_survey_api.dto.request.passing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link ru.rutmiit.user_survey_api.model.Answer} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnswerDtoRequest {
    @Positive(message = "answer.questionId should be a positive number")
    private Long questionId;

    @Size(min = 1, max = 255, message = "incorrect answer.answer size")
    private String answer;
}