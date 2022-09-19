package ru.rutmiit.user_survey_api.exception;

public class SurveyNotPassedException extends RuntimeException{
    public SurveyNotPassedException(String message) {
        super(message);
    }
}
