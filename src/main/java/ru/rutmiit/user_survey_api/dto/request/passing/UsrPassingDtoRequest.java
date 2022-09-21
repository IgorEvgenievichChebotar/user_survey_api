package ru.rutmiit.user_survey_api.dto.request.passing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A DTO for the {@link ru.rutmiit.user_survey_api.model.Usr} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsrPassingDtoRequest {
    private Long id;
    private String name;
    private String email;
}