package ru.rutmiit.user_survey_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DetailedSurveyDtoResponse {
    private Long id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private List<QuestionWithAnswerDtoResponse> questions = new ArrayList<>();
}
