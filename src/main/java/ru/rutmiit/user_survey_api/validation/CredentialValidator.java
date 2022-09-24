package ru.rutmiit.user_survey_api.validation;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.rutmiit.user_survey_api.dto.request.auth.CredentialDtoRequest;

@Component
@AllArgsConstructor
public class CredentialValidator implements Validator {
    private final UserDetailsService userDetailsService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(CredentialDtoRequest.class);
    }

    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {
        CredentialDtoRequest request = (CredentialDtoRequest) target;

        try {
            userDetailsService.loadUserByUsername(request.getUsername());
        } catch (UsernameNotFoundException ignored) {
            return;
        }

        errors.rejectValue("username", "", "this username already exists");
    }
}
