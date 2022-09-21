package ru.rutmiit.user_survey_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.rutmiit.user_survey_api.dto.request.creating.QuestionDtoRequest;
import ru.rutmiit.user_survey_api.dto.request.creating.SurveyDtoRequest;
import ru.rutmiit.user_survey_api.dto.request.passing.PassingDtoRequest;
import ru.rutmiit.user_survey_api.dto.response.SurveyDtoResponse;
import ru.rutmiit.user_survey_api.mapper.AnswerMapper;
import ru.rutmiit.user_survey_api.mapper.QuestionMapper;
import ru.rutmiit.user_survey_api.mapper.SurveyMapper;
import ru.rutmiit.user_survey_api.model.Survey;
import ru.rutmiit.user_survey_api.service.AnswerService;
import ru.rutmiit.user_survey_api.service.QuestionService;
import ru.rutmiit.user_survey_api.service.SurveyService;

import java.util.List;

import static ru.rutmiit.user_survey_api.mapper.SurveyMapper.toResponse;
import static ru.rutmiit.user_survey_api.mapper.SurveyMapper.toSurvey;
import static ru.rutmiit.user_survey_api.mapper.UsrMapper.toUsr;

@RestController
@RequestMapping("/survey")
@RequiredArgsConstructor
public class SurveyController {
    private final SurveyService surveyService;
    private final QuestionService questionService;
    private final AnswerService answerService;

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
                .toList();
    }

    @GetMapping("/{id}")
    public SurveyDtoResponse findById(@PathVariable("id") Long id) {
        return toResponse(surveyService.findById(id));
    }

    @PostMapping
    public SurveyDtoResponse create(@RequestBody SurveyDtoRequest request) {

        var survey = toSurvey(request);

        var createdSurvey = surveyService.create(survey);

        return toResponse(createdSurvey);
    }

    @PostMapping("/{id}")
    public SurveyDtoResponse addQuestions(@PathVariable("id") Long id,
                                          @RequestBody List<QuestionDtoRequest> requests) {

        var questions = requests.stream()
                .map(QuestionMapper::toQuestion)
                .toList();

        questions.forEach(q -> questionService.create(q, id));

        return toResponse(surveyService.findById(id));
    }

    @PostMapping("/{id}/pass")
    public String pass(@PathVariable("id") Long id,
                       @RequestBody PassingDtoRequest request) {

        var user = toUsr(request.getUser());

        var answers = request.getAnswers().stream()
                .map(AnswerMapper::toAnswer)
                .peek(a -> a.setUsr(user))
                .toList();

        answerService.commitAll(answers);

        return "the answers to the questions were successfully published";
    }

    @PatchMapping("/{id}")
    public SurveyDtoResponse update(@PathVariable("id") Long id,
                                    @RequestBody SurveyDtoRequest request) {

        var survey = toSurvey(request);

        var updatedSurvey = surveyService.update(survey, id);

        return toResponse(updatedSurvey);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        surveyService.deleteById(id);
    }
}

