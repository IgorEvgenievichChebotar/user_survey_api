package ru.rutmiit.user_survey_api.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rutmiit.user_survey_api.service.QuestionService;
import ru.rutmiit.user_survey_api.validation.annotation.UniqueQuestionText;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class UniqueQuestionTextValidator implements ConstraintValidator<UniqueQuestionText, String> {
    private final QuestionService questionService;

    @Override
    public void initialize(UniqueQuestionText constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !questionService.existsByText(value);
    }
}
