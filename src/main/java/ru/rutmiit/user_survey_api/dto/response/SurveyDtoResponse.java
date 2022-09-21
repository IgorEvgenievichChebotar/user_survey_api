package ru.rutmiit.user_survey_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class SurveyDtoResponse {
    private Long id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private List<QuestionDtoResponse> questions = new ArrayList<>();
}