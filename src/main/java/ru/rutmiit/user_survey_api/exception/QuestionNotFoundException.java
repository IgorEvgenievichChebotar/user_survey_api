package ru.rutmiit.user_survey_api.exception;

public class QuestionNotFoundException extends RuntimeException{
    public QuestionNotFoundException(String message) {
        super(message);
    }

    public QuestionNotFoundException() {
        super("question with this id was not found");
    }
}
