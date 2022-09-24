package ru.rutmiit.user_survey_api.mapper;


import ru.rutmiit.user_survey_api.dto.request.auth.CredentialDtoRequest;
import ru.rutmiit.user_survey_api.model.Credential;

public class CredentialMapper {
    public static Credential toCredential(CredentialDtoRequest credentialDtoRequest) {
        return new Credential(
                credentialDtoRequest.getUsername(),
                credentialDtoRequest.getPassword()
        );
    }
}
