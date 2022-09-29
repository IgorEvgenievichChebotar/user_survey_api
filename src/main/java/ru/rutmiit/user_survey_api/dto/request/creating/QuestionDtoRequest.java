package ru.rutmiit.user_survey_api.dto.request.creating;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.rutmiit.user_survey_api.validation.OnCreate;
import ru.rutmiit.user_survey_api.validation.OnUpdate;
import ru.rutmiit.user_survey_api.validation.annotation.QuestionTypeSubset;
import ru.rutmiit.user_survey_api.validation.annotation.UniqueQuestionText;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

import static ru.rutmiit.user_survey_api.model.enumeration.QuestionType.*;

/**
 * A DTO for the {@link ru.rutmiit.user_survey_api.model.Question} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuestionDtoRequest {
    @NotBlank(message = "question.text should not be blank", groups = OnCreate.class)
    @Size(min = 1, max = 255, message = "Incorrect question.text length", groups = {OnCreate.class, OnUpdate.class})
    @UniqueQuestionText(groups = {OnCreate.class, OnUpdate.class})
    private String text;

    @NotNull(message = "question.type should not be null", groups = OnCreate.class)
    @QuestionTypeSubset(anyOf = {TEXT, MULTIPLE_OPTIONS, ONE_OPTION}, groups = {OnCreate.class, OnUpdate.class})
    private String type;

    @Valid
    private List<OptionDtoRequest> options = new ArrayList<>();
}