package ru.rutmiit.user_survey_api.exception;

public class QuestionNotCreatedException extends RuntimeException{
    public QuestionNotCreatedException(String message) {
        super(message);
    }
}
