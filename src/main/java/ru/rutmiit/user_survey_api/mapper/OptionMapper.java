package ru.rutmiit.user_survey_api.mapper;

import ru.rutmiit.user_survey_api.dto.request.creating.OptionDtoRequest;
import ru.rutmiit.user_survey_api.dto.response.OptionDtoResponse;
import ru.rutmiit.user_survey_api.model.Option;

public class OptionMapper {
    public static Option toOption(OptionDtoRequest request) {
        return new Option(
                request.getText()
        );
    }

    public static OptionDtoResponse toResponse(Option option) {
        return new OptionDtoResponse(
                option.getId(),
                option.getText()
        );
    }
}
