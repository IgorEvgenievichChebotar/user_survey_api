package ru.rutmiit.user_survey_api.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rutmiit.user_survey_api.service.CredentialService;
import ru.rutmiit.user_survey_api.validation.annotation.UniqueUsername;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
    private final CredentialService credentialService;

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !credentialService.existsByUsername(value);
    }
}
