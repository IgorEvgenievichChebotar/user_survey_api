package ru.rutmiit.user_survey_api.validation;

import ru.rutmiit.user_survey_api.model.enumeration.QuestionType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class QuestionTypeSubsetValidator implements ConstraintValidator<QuestionTypeSubset, QuestionType> {
    private QuestionType[] subset;

    @Override
    public void initialize(QuestionTypeSubset constraint) {
        this.subset = constraint.anyOf();
    }

    @Override
    public boolean isValid(QuestionType value, ConstraintValidatorContext context) {
        return value == null || Arrays.asList(subset).contains(value);
    }
}
