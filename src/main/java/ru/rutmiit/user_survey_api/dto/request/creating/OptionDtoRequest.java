package ru.rutmiit.user_survey_api.dto.request.creating;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A DTO for the {@link ru.rutmiit.user_survey_api.model.Option} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OptionDtoRequest {
    private String text;
}