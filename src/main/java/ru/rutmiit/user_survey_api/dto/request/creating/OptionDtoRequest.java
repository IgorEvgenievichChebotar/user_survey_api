package ru.rutmiit.user_survey_api.dto.request.creating;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.rutmiit.user_survey_api.validation.OnCreate;
import ru.rutmiit.user_survey_api.validation.OnUpdate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link ru.rutmiit.user_survey_api.model.Option} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OptionDtoRequest {
    @NotBlank(message = "option.text should not be blank", groups = OnCreate.class)
    @Size(min = 1, max = 255, message = "incorrect option.text size", groups = {OnCreate.class, OnUpdate.class})
    private String text;
}