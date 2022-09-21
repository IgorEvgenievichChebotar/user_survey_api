package ru.rutmiit.user_survey_api.mapper;

import ru.rutmiit.user_survey_api.dto.request.passing.AnswerPassingDtoRequest;
import ru.rutmiit.user_survey_api.model.Answer;

import static ru.rutmiit.user_survey_api.mapper.QuestionMapper.toQuestion;

public class AnswerMapper {
    public static Answer toAnswer(AnswerPassingDtoRequest request){
        return new Answer(
                toQuestion(request.getQuestion()),
                request.getAnswer()
        );
    }
}
