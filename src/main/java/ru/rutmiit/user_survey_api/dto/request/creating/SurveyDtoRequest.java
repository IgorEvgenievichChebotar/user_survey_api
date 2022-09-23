package ru.rutmiit.user_survey_api.dto.request.creating;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.rutmiit.user_survey_api.validation.OnCreate;
import ru.rutmiit.user_survey_api.validation.OnUpdate;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * A DTO for the {@link ru.rutmiit.user_survey_api.model.Survey} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SurveyDtoRequest {
    @NotBlank(message = "survey.title should not be blank", groups = OnCreate.class)
    @Size(min = 1, max = 255, message = "Incorrect survey.title length", groups = {OnCreate.class, OnUpdate.class})
    private String title;

    @NotNull(message = "survey.endDate should not be null", groups = OnCreate.class)
    @Future(message = "survey.date should be a date that has not yet occurred", groups = {OnCreate.class, OnUpdate.class})
    private LocalDate endDate;

    @NotBlank(message = "survey.description should not be blank", groups = OnCreate.class)
    @Size(min = 1, max = 255, message = "Incorrect survey.description length", groups = {OnCreate.class, OnUpdate.class})
    private String description;

    @Valid
    private List<QuestionDtoRequest> questions = new ArrayList<>();
}