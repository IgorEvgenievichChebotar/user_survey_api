package ru.rutmiit.user_survey_api.mapper;

import ru.rutmiit.user_survey_api.dto.request.passing.UsrDtoRequest;
import ru.rutmiit.user_survey_api.model.Usr;

public class UsrMapper {
    public static Usr toUsr(UsrDtoRequest request) {
        if (request != null)
            return new Usr(
                    request.getId(),
                    request.getName(),
                    request.getEmail()
            );
        else {
            return new Usr();
        }
    }
}
