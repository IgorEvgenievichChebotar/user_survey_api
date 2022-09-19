package ru.rutmiit.user_survey_api.exception;

public class SurveyNotCreatedException extends RuntimeException{
    public SurveyNotCreatedException(String message) {
        super(message);
    }
}
