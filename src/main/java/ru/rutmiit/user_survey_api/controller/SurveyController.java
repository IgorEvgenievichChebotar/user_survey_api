package ru.rutmiit.user_survey_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.rutmiit.user_survey_api.dto.response.SurveyDtoResponse;
import ru.rutmiit.user_survey_api.mapper.SurveyMapper;
import ru.rutmiit.user_survey_api.model.Survey;
import ru.rutmiit.user_survey_api.service.SurveyService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/survey")
@RequiredArgsConstructor
public class SurveyController {
    private final SurveyService surveyService;

    @GetMapping
    public Iterable<SurveyDtoResponse> findMany(
            @RequestParam(
                    value = "active",
                    required = false,
                    defaultValue = "false") Boolean active,
            @RequestParam(
                    value = "user_id",
                    required = false) Long user_id) {

        List<Survey> resultList = surveyService.findAll();

        if (active)
            resultList.retainAll(surveyService.findActive());
        if (user_id != null)
            resultList.retainAll(surveyService.findPassedSurveysByUserId(user_id));

        return resultList.stream()
                .map(SurveyMapper::toResponse)
                .collect(Collectors.toSet());
    }


}
