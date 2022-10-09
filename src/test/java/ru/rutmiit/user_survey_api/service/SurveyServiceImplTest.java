package ru.rutmiit.user_survey_api.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.rutmiit.user_survey_api.exception.SurveyNotFoundException;
import ru.rutmiit.user_survey_api.exception.UserNotFoundException;
import ru.rutmiit.user_survey_api.model.Survey;
import ru.rutmiit.user_survey_api.model.Usr;
import ru.rutmiit.user_survey_api.repository.SurveyRepository;
import ru.rutmiit.user_survey_api.service.implementation.AnswerServiceImpl;
import ru.rutmiit.user_survey_api.service.implementation.SurveyServiceImpl;
import ru.rutmiit.user_survey_api.service.implementation.UsrServiceImpl;
import unit.UnitTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;
import static ru.rutmiit.user_survey_api.TestInstances.*;

/**
 * pattern:
 * - prepare
 * - execute
 * - verify
 */

@UnitTest
class SurveyServiceImplTest {
    @Mock
    private SurveyRepository surveyRepository;
    @Mock
    private UsrServiceImpl usrService;

    @Mock
    private AnswerServiceImpl answerService;

    @InjectMocks
    private SurveyServiceImpl surveyService;

    @Test
    void findById_shouldReturnSurvey() {
        doReturn(Optional.of(SURVEY1))
                .when(surveyRepository)
                .findById(SURVEY1.getId());

        Survey result = surveyService.findById(SURVEY1.getId());

        assertThat(result)
                .isEqualTo(SURVEY1);
    }

    @Test
    void findById_shouldThrowSurveyNotFoundEx_whenItIsNotExists() {
        doReturn(Optional.empty())
                .when(surveyRepository)
                .findById(any());

        assertThatThrownBy(() -> surveyService.findById(SURVEY1.getId()))
                .isExactlyInstanceOf(SurveyNotFoundException.class)
                .hasMessageContaining("not found");
    }

    @Test
    void findByTitle_shouldReturnSurvey() {
        doReturn(Optional.of(SURVEY1))
                .when(surveyRepository)
                .findByTitleIgnoreCase(SURVEY1.getTitle());

        Survey result = surveyService.findByTitle(SURVEY1.getTitle());

        assertThat(result)
                .isEqualTo(SURVEY1);
    }

    @Test
    void findByTitle_shouldThrowSurveyNotFoundEx_whenItIsNotExists() {
        doReturn(Optional.empty())
                .when(surveyRepository)
                .findByTitleIgnoreCase(any());

        assertThatThrownBy(() -> surveyService.findByTitle(SURVEY1.getTitle()))
                .isExactlyInstanceOf(SurveyNotFoundException.class)
                .hasMessageContaining("not found");
    }

    @Test
    void findActive_shouldReturnSurvey() {
        doReturn(List.of(SURVEY1, SURVEY2))
                .when(surveyRepository)
                .findAll();

        List<Survey> result = surveyService.findActive();

        assertThat(result)
                .containsOnly(SURVEY1);
    }

    @Test
    void findAll_shouldReturnListOfSurveys() {
        doReturn(List.of(SURVEY1, SURVEY2))
                .when(surveyRepository)
                .findAll();

        List<Survey> result = surveyService.findAll();

        assertThat(result)
                .contains(SURVEY1, SURVEY2);
    }

    @Test
    void findPassedSurveysByUserId_shouldReturnListOfActiveSurveys() {
        doReturn(List.of(SURVEY1))
                .when(surveyRepository)
                .findPassedSurveysByUserId(USER1.getId());
        doReturn(USER1)
                .when(usrService)
                .findById(USER1.getId());

        List<Survey> result = surveyService.findPassedSurveysByUserId(USER1.getId());

        assertThat(result)
                .containsOnly(SURVEY1);
    }

    @Test
    void findPassedSurveysByUserId_shouldThrowUsrNotFoundEx_whenHeIsNotFound() {
        doThrow(UserNotFoundException.class)
                .when(usrService)
                .findById(any());

        assertThatThrownBy(() -> surveyService.findPassedSurveysByUserId(USER1.getId()))
                .isInstanceOf(UserNotFoundException.class)/*
                .hasMessageContaining("not found")*/;

    }

    @Test
    void pass_shouldReturnUser() {
        doReturn(Optional.of(SURVEY1))
                .when(surveyRepository)
                .findById(SURVEY1.getId());
        doReturn(USER1)
                .when(usrService)
                .update(USER1);
        doNothing()
                .when(answerService)
                .commitAll(anyList());

        Usr result = surveyService.pass(SURVEY1.getId(), USER1, List.of(ANSWER1, ANSWER3));

        assertThat(result)
                .isEqualTo(USER1);
        assertAll(
                () -> assertThat(ANSWER1.getUsr())
                        .isEqualTo(USER1),
                () -> assertThat(ANSWER3.getUsr())
                        .isEqualTo(USER1),
                () -> assertThat(ANSWER1.getSurvey())
                        .isEqualTo(SURVEY1),
                () -> assertThat(ANSWER3.getSurvey())
                        .isEqualTo(SURVEY1)
        );
    }

    @Test
    void pass_shouldThrowSurveyNotFoundEx_whenItIsNotExists() {
        doReturn(Optional.empty())
                .when(surveyRepository)
                .findById(any());

        assertThatThrownBy(() -> surveyService.pass(SURVEY1.getId(), USER1, List.of(ANSWER1, ANSWER3)))
                .isExactlyInstanceOf(SurveyNotFoundException.class)
                .hasMessageContaining("not found");
    }

    @Test
    void create_shouldReturnSurvey() {
        doReturn(SURVEY1)
                .when(surveyRepository)
                .save(SURVEY1.setStartDate(null));

        Survey result = surveyService.create(SURVEY1);

        assertThat(result)
                .isEqualTo(SURVEY1);
    }

    @Test
    void update() {
        doReturn(Optional.of(SURVEY1))
                .when(surveyRepository)
                .findById(SURVEY1.getId());
        doReturn(SURVEY1)
                .when(surveyRepository)
                .save(SURVEY1);

        Survey result = surveyService.update(SURVEY1, SURVEY1.getId());

        assertThat(result)
                .isEqualTo(SURVEY1);
    }

    @Test
    void update_shouldThrowSurveyNotFoundEx_whenItIsNotExists() {
        doReturn(Optional.empty())
                .when(surveyRepository)
                .findById(SURVEY1.getId());

        assertThatThrownBy(() -> surveyService.update(SURVEY1, SURVEY1.getId()))
                .isInstanceOf(SurveyNotFoundException.class)
                .hasMessageContaining("not found");
    }

    @Test
    void deleteById() {
        doReturn(true)
                .when(surveyRepository)
                .existsById(SURVEY1.getId());
        doNothing()
                .when(surveyRepository)
                .deleteById(any());

        surveyService.deleteById(SURVEY1.getId());

        verify(surveyRepository).existsById(any());
        verify(surveyRepository).deleteById(SURVEY1.getId());
    }
}