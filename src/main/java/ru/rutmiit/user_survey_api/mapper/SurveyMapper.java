package ru.rutmiit.user_survey_api.mapper;

import ru.rutmiit.user_survey_api.dto.request.creating.SurveyDtoRequest;
import ru.rutmiit.user_survey_api.dto.response.SurveyDtoResponse;
import ru.rutmiit.user_survey_api.model.Survey;

public class SurveyMapper {

    public static Survey toSurvey(SurveyDtoRequest request) {
        return new Survey(
                request.getId(),
                request.getTitle(),
                request.getEndDate(),
                request.getDescription(),
                request.getQuestions().stream()
                        .map(QuestionMapper::toQuestion)
                        .toList()
        );
    }

    public static SurveyDtoResponse toResponse(Survey survey) {
        return new SurveyDtoResponse(
                survey.getId(),
                survey.getTitle(),
                survey.getStartDate(),
                survey.getEndDate(),
                survey.getDescription(),
                survey.getQuestions().stream()
                        .map(QuestionMapper::toResponse)
                        .toList()
        );
    }


}
