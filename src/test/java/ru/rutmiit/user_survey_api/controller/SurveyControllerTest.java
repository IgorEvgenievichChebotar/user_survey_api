package ru.rutmiit.user_survey_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import integration.IntegrationTest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.rutmiit.user_survey_api.mapper.SurveyMapper;
import ru.rutmiit.user_survey_api.model.Option;
import ru.rutmiit.user_survey_api.model.Question;
import ru.rutmiit.user_survey_api.model.Survey;
import ru.rutmiit.user_survey_api.model.enumeration.QuestionType;
import ru.rutmiit.user_survey_api.repository.SurveyRepository;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * pattern:
 * - prepare
 * - execute
 * - verify
 */

@IntegrationTest
@WithMockUser(
        username = "username",
        authorities = {"ADMIN"}) // проходит авторизацию под тестовым пользователем
@RequiredArgsConstructor
class SurveyControllerTest {
    private final SurveyRepository surveyRepository;

    private final ObjectMapper objectMapper;
    private final MockMvc mockMvc;

    private static final Survey SURVEY1 = Survey.builder()
            .title("SurveyTitle1")
            .startDate(LocalDate.of(2020, 1, 20))
            .endDate(LocalDate.of(2023, 8, 10))
            .description("SurveyDescription1")
            .questions(List.of(
                    Question.builder()
                            .text("QuestionText1")
                            .type(QuestionType.TEXT)
                            .options(List.of(
                                    new Option("OptionText1"),
                                    new Option("OptionText2")
                            ))
                            .build(),
                    Question.builder()
                            .text("QuestionText2")
                            .type(QuestionType.TEXT)
                            .options(List.of(
                                    new Option("OptionText3"),
                                    new Option("OptionText4")
                            ))
                            .build()
            ))
            .build();

    @BeforeEach
    void prepare() {
        surveyRepository.deleteAllInBatch();
    }

    @Test
    void findMany_shouldReturnAllSurveysAndStatus200() throws Exception {
        surveyRepository.save(SURVEY1);

        this.mockMvc.perform(get("/survey"))

                .andExpect(content().json(
                        objectMapper.writeValueAsString(
                                List.of(SurveyMapper.toResponse(SURVEY1))
                        )))
                .andDo(print());

    }

    @Test
    void findById() {
    }

    @Test
    void create() {
    }

    @Test
    void addQuestions() {
    }

    @Test
    void pass() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}