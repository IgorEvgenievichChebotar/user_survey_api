package ru.rutmiit.user_survey_api.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rutmiit.user_survey_api.service.SurveyService;
import ru.rutmiit.user_survey_api.validation.annotation.UniqueSurveyTitle;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class UniqueSurveyTitleValidator implements ConstraintValidator<UniqueSurveyTitle, String> {
    private final SurveyService surveyService;

    @Override
    public void initialize(UniqueSurveyTitle constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !surveyService.existsByTitle(value);
    }
}
