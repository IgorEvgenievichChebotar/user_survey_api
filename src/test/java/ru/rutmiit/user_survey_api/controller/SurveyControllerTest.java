package ru.rutmiit.user_survey_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.rutmiit.user_survey_api.repository.SurveyRepository;
import unit.UnitTest;

/**
 * pattern:
 * - prepare
 * - execute
 * - verify
 */

@UnitTest
@WebMvcTest
@RequiredArgsConstructor
class SurveyControllerTest {
    private final SurveyRepository surveyRepository;

    private final ObjectMapper objectMapper;
    private final MockMvc mockMvc;

/*    @BeforeEach
    void prepare() {
        surveyRepository.deleteAllInBatch();
    }*/

    @Test
    void findMany_shouldReturnAllSurveysAndStatus200() {
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