package ru.rutmiit.user_survey_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.rutmiit.user_survey_api.dto.request.creating.QuestionDtoRequest;
import ru.rutmiit.user_survey_api.dto.request.creating.SurveyDtoRequest;
import ru.rutmiit.user_survey_api.dto.request.passing.AnswerDtoRequest;
import ru.rutmiit.user_survey_api.dto.request.passing.PassingDtoRequest;
import ru.rutmiit.user_survey_api.dto.response.SurveyDtoResponse;
import ru.rutmiit.user_survey_api.exception.QuestionNotCreatedException;
import ru.rutmiit.user_survey_api.exception.SurveyNotCreatedException;
import ru.rutmiit.user_survey_api.exception.SurveyNotUpdatedException;
import ru.rutmiit.user_survey_api.mapper.QuestionMapper;
import ru.rutmiit.user_survey_api.mapper.SurveyMapper;
import ru.rutmiit.user_survey_api.model.Answer;
import ru.rutmiit.user_survey_api.model.Question;
import ru.rutmiit.user_survey_api.model.Survey;
import ru.rutmiit.user_survey_api.model.Usr;
import ru.rutmiit.user_survey_api.service.QuestionService;
import ru.rutmiit.user_survey_api.service.SurveyService;
import ru.rutmiit.user_survey_api.util.ExceptionMessageBuilder;
import ru.rutmiit.user_survey_api.validation.OnCreate;
import ru.rutmiit.user_survey_api.validation.OnUpdate;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
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
                    .collect(toList());
        }

        return resultList.stream()
                .map(SurveyMapper::toResponse)
                .collect(toList());
    }

    @GetMapping("/{id}")
    public SurveyDtoResponse findById(@PathVariable("id") Long id) {
        return toResponse(surveyService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SurveyDtoResponse create(@RequestBody @Validated(OnCreate.class) SurveyDtoRequest request,
                                    BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            String msg = ExceptionMessageBuilder.buildMessage(bindingResult);
            throw new SurveyNotCreatedException(msg);
        }

        Survey survey = toSurvey(request);

        Survey createdSurvey = surveyService.create(survey);

        return toResponse(createdSurvey);
    }

    @PostMapping("/{id}")
    public SurveyDtoResponse addQuestions(@PathVariable("id") Long id,
                                          @RequestBody @Valid List<QuestionDtoRequest> requests,
                                          BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            String msg = ExceptionMessageBuilder.buildMessage(bindingResult);
            throw new QuestionNotCreatedException(msg);
        }

        List<Question> questions = requests.stream()
                .map(QuestionMapper::toQuestion)
                .toList();

        questionService.createAll(questions, id);

        return toResponse(surveyService.findById(id));
    }

    @PostMapping("/{id}/pass")
    public String pass(@PathVariable("id") Long surveyId,
                       @RequestBody @Valid PassingDtoRequest request) {

        Usr user = toUsr(request.getUser());

        List<Answer> answers = getAnswersFromRequest(surveyId, request);

        Usr savedOrUpdatedUser = surveyService.pass(surveyId, user, answers);

        return "Successful, your answers were saved for the user with id " + savedOrUpdatedUser.getId();
    }

    @PatchMapping("/{id}")
    public SurveyDtoResponse update(@PathVariable("id") Long id,
                                    @RequestBody @Validated(OnUpdate.class) SurveyDtoRequest request,
                                    BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            String msg = ExceptionMessageBuilder.buildMessage(bindingResult);
            throw new SurveyNotUpdatedException(msg);
        }

        Survey survey = toSurvey(request);

        Survey updatedSurvey = surveyService.update(survey, id);

        return toResponse(updatedSurvey);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        surveyService.deleteById(id);
    }

    private List<Answer> getAnswersFromRequest(Long surveyId, PassingDtoRequest request) {
        Map<Long, Question> allQuestions =
                questionService.findAllBySurveyId(surveyId).stream()
                .collect(toMap(Question::getId, q -> q));

        List<Answer> answers = new ArrayList<>();
        for (AnswerDtoRequest a : request.getAnswers())
            if (allQuestions.containsKey(a.getQuestionId()))
                answers.add(
                        Answer.builder()
                                .answer(a.getAnswer())
                                .question(allQuestions.get(a.getQuestionId()))
                                .build());

        return answers;
    }
}

