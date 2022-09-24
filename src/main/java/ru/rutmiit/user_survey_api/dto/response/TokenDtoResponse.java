package ru.rutmiit.user_survey_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenDtoResponse {
    private String token;
    private String tokenType = "Bearer";

    public TokenDtoResponse(String token) {
        this.token = token;
    }
}
