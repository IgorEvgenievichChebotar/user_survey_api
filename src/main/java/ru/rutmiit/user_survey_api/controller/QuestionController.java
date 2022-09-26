package ru.rutmiit.user_survey_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.rutmiit.user_survey_api.dto.request.creating.QuestionDtoRequest;
import ru.rutmiit.user_survey_api.dto.response.QuestionDtoResponse;
import ru.rutmiit.user_survey_api.exception.QuestionNotCreatedException;
import ru.rutmiit.user_survey_api.exception.QuestionNotUpdatedException;
import ru.rutmiit.user_survey_api.service.QuestionService;
import ru.rutmiit.user_survey_api.util.ExceptionMessageBuilder;
import ru.rutmiit.user_survey_api.validation.OnCreate;
import ru.rutmiit.user_survey_api.validation.QuestionValidator;

import javax.validation.Valid;

import static ru.rutmiit.user_survey_api.mapper.QuestionMapper.toQuestion;
import static ru.rutmiit.user_survey_api.mapper.QuestionMapper.toResponse;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionValidator questionValidator;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionDtoResponse create(@RequestBody @Validated(OnCreate.class) QuestionDtoRequest request,
                                      BindingResult bindingResult,
                                      @RequestParam(
                                              value = "survey_id",
                                              required = false) Long id) {

        questionValidator.validate(request, bindingResult);

        if (bindingResult.hasErrors()){
            var msg = ExceptionMessageBuilder.buildMessage(bindingResult);
            throw new QuestionNotCreatedException(msg);
        }

        var question = toQuestion(request);

        var createdQuestion = questionService.create(question, id);

        return toResponse(createdQuestion);
    }

    @PatchMapping("/{id}")
    public QuestionDtoResponse update(@RequestBody @Valid QuestionDtoRequest request,
                                      BindingResult bindingResult,
                                      @PathVariable("id") Long id) {

        questionValidator.validate(request, bindingResult);
        if (bindingResult.hasErrors()){
            var msg = ExceptionMessageBuilder.buildMessage(bindingResult);
            throw new QuestionNotUpdatedException(msg);
        }

        var question = toQuestion(request);

        var updatedQuestion = questionService.update(question, id);

        return toResponse(updatedQuestion);
    }
}
