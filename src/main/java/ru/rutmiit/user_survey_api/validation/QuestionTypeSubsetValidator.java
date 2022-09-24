package ru.rutmiit.user_survey_api.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class QuestionTypeSubsetValidator implements ConstraintValidator<QuestionTypeSubset, String> {
    private String[] subset;

    @Override
    public void initialize(QuestionTypeSubset constraint) {
        this.subset = Arrays.stream(constraint.anyOf())
                .map(Enum::toString)
                .toArray(String[]::new);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || Arrays.asList(subset).contains(value.toUpperCase());
    }
}
