package ru.rutmiit.user_survey_api.exception;

public class SurveyNotFoundException extends RuntimeException{
    public SurveyNotFoundException(String message) {
        super(message);
    }

    public SurveyNotFoundException() {
        super("survey with this id was not found");
    }
}
