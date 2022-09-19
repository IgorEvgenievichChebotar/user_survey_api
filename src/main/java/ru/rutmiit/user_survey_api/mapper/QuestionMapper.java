package ru.rutmiit.user_survey_api.mapper;

import ru.rutmiit.user_survey_api.dto.request.creating.QuestionDtoCreatingRequest;
import ru.rutmiit.user_survey_api.dto.response.QuestionDtoResponse;
import ru.rutmiit.user_survey_api.model.Question;

import java.util.stream.Collectors;

public class QuestionMapper {
    public static Question toQuestion(QuestionDtoCreatingRequest creatingRequest) {
        return new Question(
                creatingRequest.getText(),
                creatingRequest.getType(),
                creatingRequest.getOptions().stream()
                        .map(OptionMapper::toOption)
                        .collect(Collectors.toSet())
        );
    }

    public static QuestionDtoResponse toResponse(Question question) {
        return new QuestionDtoResponse(
                question.getId(),
                question.getText(),
                question.getOptions().stream()
                        .map(OptionMapper::toResponse)
                        .collect(Collectors.toSet()),
                question.getType()
        );
    }

}
