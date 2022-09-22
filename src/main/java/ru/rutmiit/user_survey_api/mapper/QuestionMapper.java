package ru.rutmiit.user_survey_api.mapper;

import ru.rutmiit.user_survey_api.dto.request.creating.QuestionDtoRequest;
import ru.rutmiit.user_survey_api.dto.response.QuestionDtoResponse;
import ru.rutmiit.user_survey_api.dto.response.QuestionsWithAnswerDtoResponse;
import ru.rutmiit.user_survey_api.model.Answer;
import ru.rutmiit.user_survey_api.model.Question;

public class QuestionMapper {
    public static Question toQuestion(QuestionDtoRequest request) {
        return new Question(
                request.getText(),
                request.getType(),
                request.getOptions().stream()
                        .map(OptionMapper::toOption)
                        .toList()
        );
    }

    public static QuestionDtoResponse toResponse(Question question) {
        return new QuestionDtoResponse(
                question.getId(),
                question.getText(),
                question.getOptions().stream()
                        .map(OptionMapper::toResponse)
                        .toList(),
                question.getType()
        );
    }

    public static QuestionsWithAnswerDtoResponse toDetailedResponse(Question question) {
        return new QuestionsWithAnswerDtoResponse(
                question.getId(),
                question.getText(),
                question.getOptions().stream()
                        .map(OptionMapper::toResponse)
                        .toList(),
                question.getType(),
                question.getAnswers().stream()
                        .filter(a -> a.getQuestion().getId().equals(question.getId()))
                        .findFirst()
                        .orElse(new Answer())
                        .toString()
        );
    }

}
