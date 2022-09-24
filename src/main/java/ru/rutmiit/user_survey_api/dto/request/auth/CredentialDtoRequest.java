package ru.rutmiit.user_survey_api.dto.request.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.rutmiit.user_survey_api.validation.OnCreate;
import ru.rutmiit.user_survey_api.validation.OnUpdate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CredentialDtoRequest {
    @NotBlank(groups = OnCreate.class)
    @Size(min = 1, max = 255, message = "incorrect username size", groups = {OnCreate.class, OnUpdate.class})
    private String username;

    @NotBlank(groups = OnCreate.class)
    @Size(min = 1, max = 255, message = "incorrect password size", groups = {OnCreate.class, OnUpdate.class})
    private String password;
}
