package ru.rutmiit.user_survey_api.mapper;

import ru.rutmiit.user_survey_api.dto.request.passing.UsrPassingDtoRequest;
import ru.rutmiit.user_survey_api.model.Usr;

public class UsrMapper {
    public static Usr toUsr(UsrPassingDtoRequest request){
        return new Usr(
                request.getId(),
                request.getName(),
                request.getEmail()
        );
    }
}
