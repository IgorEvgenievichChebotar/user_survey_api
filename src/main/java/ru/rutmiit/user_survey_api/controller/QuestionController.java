package ru.rutmiit.user_survey_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.rutmiit.user_survey_api.dto.request.creating.QuestionDtoRequest;
import ru.rutmiit.user_survey_api.dto.response.QuestionDtoResponse;
import ru.rutmiit.user_survey_api.service.QuestionService;

import static ru.rutmiit.user_survey_api.mapper.QuestionMapper.toQuestion;
import static ru.rutmiit.user_survey_api.mapper.QuestionMapper.toResponse;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping
    public QuestionDtoResponse create(@RequestBody QuestionDtoRequest request,
                                      @RequestParam(
                                              value = "survey_id",
                                              required = false) Long id) {

        var question = toQuestion(request);

        var createdQuestion = questionService.create(question, id);

        return toResponse(createdQuestion);
    }

    @PatchMapping("/{id}")
    public QuestionDtoResponse update(@RequestBody QuestionDtoRequest request,
                                      @PathVariable("id") Long id) {

        var question = toQuestion(request);

        var updatedQuestion = questionService.update(question, id);

        return toResponse(updatedQuestion);
    }
}
