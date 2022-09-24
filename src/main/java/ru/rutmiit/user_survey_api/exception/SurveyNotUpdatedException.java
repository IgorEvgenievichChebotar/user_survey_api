package ru.rutmiit.user_survey_api.exception;

public class SurveyNotUpdatedException extends RuntimeException{
    public SurveyNotUpdatedException(String message) {
        super(message);
    }

    public SurveyNotUpdatedException() {
        super("survey with this id was not found");
    }
}
