package ru.rutmiit.user_survey_api.exception;

public class UserWasNotRegisteredException extends RuntimeException{
    public UserWasNotRegisteredException(String message) {
        super(message);
    }
}
