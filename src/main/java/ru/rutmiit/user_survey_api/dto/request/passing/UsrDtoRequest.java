package ru.rutmiit.user_survey_api.dto.request.passing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link ru.rutmiit.user_survey_api.model.Usr} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsrDtoRequest {
    @Positive(message = "user.id should be a positive number")
    private Long id;

    @Size(min = 1, max = 255, message = "incorrect user.name size")
    private String name;

    @Email(message = "user.email should be valid")
    private String email;
}