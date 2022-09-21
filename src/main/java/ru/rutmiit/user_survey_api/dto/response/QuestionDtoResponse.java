package ru.rutmiit.user_survey_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.rutmiit.user_survey_api.model.Question;
import ru.rutmiit.user_survey_api.model.enumeration.QuestionType;

import java.util.ArrayList;
import java.util.List;

/**
 * A DTO for the {@link Question} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuestionDtoResponse {
    private Long id;
    private String text;
    private List<OptionDtoResponse> options = new ArrayList<>();
    private QuestionType type;
}