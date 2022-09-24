package ru.rutmiit.user_survey_api.exception;

public class QuestionNotUpdatedException extends RuntimeException{
    public QuestionNotUpdatedException(String message) {
        super(message);
    }
}
