package ru.rutmiit.user_survey_api.exception;

public class UsrNotFoundException extends RuntimeException{
    public UsrNotFoundException(String message) {
        super(message);
    }

    public UsrNotFoundException() {
        super("user with this id was not found");
    }
}
