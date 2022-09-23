package ru.rutmiit.user_survey_api.dto.request.passing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PassingDtoRequest {
    @Valid
    private UsrDtoRequest user;

    @Valid
    private List<AnswerDtoRequest> answers = new ArrayList<>();
}
