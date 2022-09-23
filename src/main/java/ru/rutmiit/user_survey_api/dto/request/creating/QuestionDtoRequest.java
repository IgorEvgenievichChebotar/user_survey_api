package ru.rutmiit.user_survey_api.dto.request.creating;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.rutmiit.user_survey_api.model.enumeration.QuestionType;
import ru.rutmiit.user_survey_api.validation.OnCreate;
import ru.rutmiit.user_survey_api.validation.OnUpdate;
import ru.rutmiit.user_survey_api.validation.ValueOfEnum;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

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
    private String text;

    @NotBlank(message = "question.type should not be blank", groups = OnCreate.class)
    @ValueOfEnum(enumClass = QuestionType.class, groups = {OnCreate.class, OnUpdate.class})
    private QuestionType type;

    @Valid
    private List<OptionDtoRequest> options = new ArrayList<>();
}