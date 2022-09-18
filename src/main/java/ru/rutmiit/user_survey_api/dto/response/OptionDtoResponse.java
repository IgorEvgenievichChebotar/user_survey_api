package ru.rutmiit.user_survey_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.rutmiit.user_survey_api.model.Option;

/**
 * A DTO for the {@link Option} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OptionDtoResponse {
    private Long id;
    private String text;
}