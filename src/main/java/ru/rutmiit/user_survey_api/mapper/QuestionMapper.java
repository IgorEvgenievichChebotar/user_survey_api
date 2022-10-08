package ru.rutmiit.user_survey_api.mapper;

import ru.rutmiit.user_survey_api.dto.request.creating.QuestionDtoRequest;
import ru.rutmiit.user_survey_api.dto.response.QuestionDtoResponse;
import ru.rutmiit.user_survey_api.dto.response.QuestionsWithAnswerDtoResponse;
import ru.rutmiit.user_survey_api.model.Question;
import ru.rutmiit.user_survey_api.model.enumeration.QuestionType;

public class QuestionMapper {
    public static Question toQuestion(QuestionDtoRequest request) {
        var question = new Question();
        return question
                .setText(request.getText())
                .setType(QuestionType.valueOf(request.getType().toUpperCase()))
                .setOptions(request.getOptions().stream()
                        .map(OptionMapper::toOption)
                        .peek(o -> o.setQuestion(question))
                        .toList());
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
                question.getAnswer().getAnswer()
        );
    }

}
