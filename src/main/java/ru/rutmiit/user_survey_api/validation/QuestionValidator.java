package ru.rutmiit.user_survey_api.validation;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.rutmiit.user_survey_api.dto.request.creating.QuestionDtoRequest;
import ru.rutmiit.user_survey_api.service.QuestionService;

@Component
@AllArgsConstructor
public class QuestionValidator implements Validator {
    private final QuestionService questionService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(QuestionDtoRequest.class);
    }

    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {
        QuestionDtoRequest request = (QuestionDtoRequest) target;

        try {
            questionService.findByText(request.getText());
        } catch (RuntimeException ignored) {
            return;
        }

        errors.rejectValue("text", "", "text like this already exists");
    }
}
