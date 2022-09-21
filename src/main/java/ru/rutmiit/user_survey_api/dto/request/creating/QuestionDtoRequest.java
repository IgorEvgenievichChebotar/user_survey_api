package ru.rutmiit.user_survey_api.dto.request.creating;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.rutmiit.user_survey_api.model.enumeration.QuestionType;

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
    private String text;
    private QuestionType type;
    private List<OptionDtoRequest> options = new ArrayList<>();
}