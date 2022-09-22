package ru.rutmiit.user_survey_api.mapper;

import ru.rutmiit.user_survey_api.dto.request.passing.AnswerDtoRequest;
import ru.rutmiit.user_survey_api.dto.response.AnswerDtoResponse;
import ru.rutmiit.user_survey_api.model.Answer;

public class AnswerMapper {
    public static Answer toAnswer(AnswerDtoRequest request){
        return new Answer(
                request.getAnswer()
        );
    }

    public static AnswerDtoResponse toResponse(Answer answer) {
        return new AnswerDtoResponse(
                answer.getAnswer()
        );
    }
}
