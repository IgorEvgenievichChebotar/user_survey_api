package ru.rutmiit.user_survey_api.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException() {
        super("user with this id was not found");
    }
}
