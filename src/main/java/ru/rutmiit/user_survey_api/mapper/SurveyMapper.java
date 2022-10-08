package ru.rutmiit.user_survey_api.mapper;

import ru.rutmiit.user_survey_api.dto.request.creating.SurveyDtoRequest;
import ru.rutmiit.user_survey_api.dto.response.DetailedSurveyDtoResponse;
import ru.rutmiit.user_survey_api.dto.response.SurveyDtoResponse;
import ru.rutmiit.user_survey_api.model.Survey;

public class SurveyMapper {

    public static Survey toSurvey(SurveyDtoRequest request) {
        var survey = new Survey();
        return survey
                .setTitle(request.getTitle())
                .setEndDate(request.getEndDate())
                .setDescription(request.getDescription())
                .setQuestions(request.getQuestions().stream()
                        .map(QuestionMapper::toQuestion)
                        .peek(q -> q.setSurvey(survey))
                        .toList());
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

    public static DetailedSurveyDtoResponse toDetailedResponse(Survey survey) {
        return new DetailedSurveyDtoResponse(
                survey.getId(),
                survey.getTitle(),
                survey.getStartDate(),
                survey.getEndDate(),
                survey.getDescription(),
                survey.getQuestions().stream()
                        .map(QuestionMapper::toDetailedResponse)
                        .toList()
        );
    }


}
