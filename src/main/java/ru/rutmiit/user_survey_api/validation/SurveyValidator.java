package ru.rutmiit.user_survey_api.validation;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.rutmiit.user_survey_api.dto.request.creating.SurveyDtoRequest;
import ru.rutmiit.user_survey_api.service.SurveyService;

@Component
@AllArgsConstructor
public class SurveyValidator implements Validator {
    private final SurveyService surveyService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(SurveyDtoRequest.class);
    }

    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {
        SurveyDtoRequest request = (SurveyDtoRequest) target;

        try {
            surveyService.findByTitle(request.getTitle());
        } catch (RuntimeException ignored) {
            return;
        }

        errors.rejectValue("title", "", "this title already exists");
    }
}
