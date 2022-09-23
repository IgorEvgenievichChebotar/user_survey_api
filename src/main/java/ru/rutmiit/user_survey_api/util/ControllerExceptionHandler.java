package ru.rutmiit.user_survey_api.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.rutmiit.user_survey_api.exception.QuestionNotFoundException;
import ru.rutmiit.user_survey_api.exception.SurveyNotFoundException;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleException(RuntimeException e){
        return getExceptionResponse(e);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleException(SurveyNotFoundException e){
        return getExceptionResponse(e);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleException(QuestionNotFoundException e){
        return getExceptionResponse(e);
    }

    private ExceptionResponse getExceptionResponse(RuntimeException e) {
        log.error(e.toString());
        return new ExceptionResponse(
                e.getMessage(),
                LocalDateTime.now()
        );
    }
}
