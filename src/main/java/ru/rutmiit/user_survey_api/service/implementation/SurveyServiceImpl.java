package ru.rutmiit.user_survey_api.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rutmiit.user_survey_api.exception.SurveyNotFoundException;
import ru.rutmiit.user_survey_api.model.*;
import ru.rutmiit.user_survey_api.repository.SurveyRepository;
import ru.rutmiit.user_survey_api.service.AnswerService;
import ru.rutmiit.user_survey_api.service.SurveyService;
import ru.rutmiit.user_survey_api.service.UsrService;

import java.time.LocalDate;
import java.util.List;

import static java.time.LocalDateTime.now;
import static ru.rutmiit.user_survey_api.mapper.FieldMapper.mapNonNullFields;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SurveyServiceImpl implements SurveyService {
    private final SurveyRepository surveyRepository;
    private final AnswerService answerService;
    private final UsrService usrService;

    @Override
    public Survey findById(Long id) {
        return surveyRepository.findById(id)
                .orElseThrow(SurveyNotFoundException::new);
    }

    @Override
    public Survey findByTitle(String title) {
        return surveyRepository.findByTitleIgnoreCase(title)
                .orElseThrow(SurveyNotFoundException::new);
    }

    @Override
    public List<Survey> findActive() {
        return this.findAll().stream()
                .filter(s -> now().isBefore(s.getEndDate().atStartOfDay()))
                .toList();
    }

    @Override
    public List<Survey> findAll() {
        return surveyRepository.findAll();
    }

    @Override
    @Transactional
    public Usr pass(Long surveyId, Usr user, List<Answer> answers) {
        var foundedSurvey = this.findById(surveyId);

        var savedOrUpdatedUser = usrService.update(user);

        for (Answer a : answers) {
            a.setUsr(savedOrUpdatedUser);
            a.setSurvey(foundedSurvey);
        }

        answerService.commitAll(answers);

        return savedOrUpdatedUser;
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Survey> findPassedSurveysByUserId(Long id) {
        usrService.findById(id);

        return surveyRepository.findPassedSurveysByUserId(id);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN')")
    public Survey create(Survey survey) {
        if (survey.getStartDate() == null)
            survey.setStartDate(LocalDate.now());

        for (Question q : survey.getQuestions()) {
            q.setSurvey(survey);
            for (Option o : q.getOptions())
                o.setQuestion(q);
        }

        return surveyRepository.save(survey);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN')")
    public Survey update(Survey survey, Long id) {
        var foundedSurvey = this.findById(id);

        mapNonNullFields(survey, foundedSurvey);

        return surveyRepository.save(foundedSurvey);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteById(Long id) {
        if (surveyRepository.existsById(id))
            surveyRepository.deleteById(id);
        else throw new SurveyNotFoundException();
    }
}
