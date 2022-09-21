package ru.rutmiit.user_survey_api.util;

import org.springframework.validation.BindingResult;

public class ExceptionMessageBuilder {
    public static String buildMessage(BindingResult bindingResult) {
        var errorMsg = new StringBuilder();
        bindingResult.getFieldErrors().forEach(error -> errorMsg
                .append(error.getField())
                .append(" - ")
                .append(error.getDefaultMessage())
                .append("; "));

        return errorMsg.toString();
    }
}