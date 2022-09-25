package ru.rutmiit.user_survey_api.util;

import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.rutmiit.user_survey_api.exception.QuestionNotFoundException;
import ru.rutmiit.user_survey_api.exception.SurveyNotFoundException;
import ru.rutmiit.user_survey_api.exception.UserNotFoundException;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleException(SurveyNotFoundException e) {
        return buildExceptionResponse(e);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleException(UsernameNotFoundException e) {
        return buildExceptionResponse(e);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleException(UserNotFoundException e) {
        return buildExceptionResponse(e);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleException(QuestionNotFoundException e) {
        return buildExceptionResponse(e);
    }

    @ExceptionHandler
    @ResponseStatus(BAD_REQUEST)
    private ExceptionResponse handleException(JWTVerificationException e) {
        return buildExceptionResponse(e);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleException(RuntimeException e) {
        return buildExceptionResponse(e);
    }

    private ExceptionResponse buildExceptionResponse(Exception e) {
        log.error(e.toString());
        return new ExceptionResponse(
                e.getMessage(),
                LocalDateTime.now()
        );
    }
}
