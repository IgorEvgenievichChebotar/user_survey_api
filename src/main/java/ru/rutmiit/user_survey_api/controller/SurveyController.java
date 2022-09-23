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
import ru.rutmiit.user_survey_api.model.Answer;
import ru.rutmiit.user_survey_api.model.Question;
import ru.rutmiit.user_survey_api.model.Survey;
import ru.rutmiit.user_survey_api.service.QuestionService;
import ru.rutmiit.user_survey_api.service.SurveyService;
import ru.rutmiit.user_survey_api.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.rutmiit.user_survey_api.mapper.SurveyMapper.toResponse;
import static ru.rutmiit.user_survey_api.mapper.SurveyMapper.toSurvey;
import static ru.rutmiit.user_survey_api.mapper.UsrMapper.toUsr;

@RestController
@RequestMapping("/survey")
@RequiredArgsConstructor
public class SurveyController {
    private final SurveyService surveyService;
    private final QuestionService questionService;

    @GetMapping
    public Iterable<Object> findMany(
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
        if (user_id != null) {
            resultList.retainAll(surveyService.findPassedSurveysByUserId(user_id));
            return resultList.stream()
                    .map(SurveyMapper::toDetailedResponse)
                    .collect(Collectors.toList());
        }

        return resultList.stream()
                .map(SurveyMapper::toResponse)
                .collect(Collectors.toList());
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
    public Map<String, String> pass(@PathVariable("id") Long surveyId,
                                    @RequestBody PassingDtoRequest request) {

        var user = toUsr(request.getUser());
        var questions = new ArrayList<Question>();
        var answers = request.getAnswers().stream()
                .peek(answerDto -> questions.add(questionService.findById(answerDto.getQuestionId())))
                .map(AnswerMapper::toAnswer)
                .toList();

        CollectionUtils.zip(answers, questions, Answer::setQuestion);

        surveyService.pass(surveyId, user, answers);

        return Map.of("message", "the answers to the questions were successfully published");
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

