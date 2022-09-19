package ru.rutmiit.user_survey_api.mapper;

import ru.rutmiit.user_survey_api.dto.request.creating.SurveyDtoCreatingRequest;
import ru.rutmiit.user_survey_api.dto.response.SurveyDtoResponse;
import ru.rutmiit.user_survey_api.model.Survey;

import java.util.stream.Collectors;

public class SurveyMapper {

    public static Survey toSurvey(SurveyDtoCreatingRequest creatingRequest) {
        return new Survey(
                creatingRequest.getTitle(),
                creatingRequest.getEndDate(),
                creatingRequest.getDescription(),
                creatingRequest.getQuestions().stream()
                        .map(QuestionMapper::toQuestion)
                        .collect(Collectors.toSet())
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
                        .collect(Collectors.toSet())
        );
    }
}
