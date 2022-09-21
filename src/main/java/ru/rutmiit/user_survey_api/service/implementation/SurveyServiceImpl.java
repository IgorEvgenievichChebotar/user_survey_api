package ru.rutmiit.user_survey_api.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rutmiit.user_survey_api.exception.SurveyNotFoundException;
import ru.rutmiit.user_survey_api.model.Survey;
import ru.rutmiit.user_survey_api.repository.SurveyRepository;
import ru.rutmiit.user_survey_api.service.SurveyService;

import java.time.LocalDate;
import java.util.List;

import static java.time.LocalDateTime.now;
import static ru.rutmiit.user_survey_api.mapper.ReflectionFieldMapper.mapNonNullFields;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SurveyServiceImpl implements SurveyService {
    private final SurveyRepository surveyRepository;

    @Override
    public Survey findById(Long id) {
        return surveyRepository.findById(id)
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
    public void pass(Survey survey, Long id) {
    }

    @Override
    public List<Survey> findPassedSurveysByUserId(Long id) {
        return surveyRepository.findPassedSurveysByUserId(id);
    }

    @Override
    @Transactional
    public Survey create(Survey survey) {
        if (survey.getStartDate() == null) survey.setStartDate(LocalDate.now());

        survey.getQuestions().forEach(q -> {
            q.setSurvey(survey);
            q.getOptions().forEach(o -> o.setQuestion(q));
        });

        return surveyRepository.save(survey);
    }

    @Override
    @Transactional
    public Survey update(Survey survey, Long id) {
        var foundedSurvey = this.findById(id);

        mapNonNullFields(survey, foundedSurvey);

        var questions = survey.getQuestions();
        var foundedQuestions = foundedSurvey.getQuestions();
        for (int i = 0; i < questions.size(); i++) {
            var question = questions.get(i);
            var foundedQuestion = foundedQuestions.get(i);
            mapNonNullFields(question, foundedQuestion);
        }

        return surveyRepository.save(foundedSurvey);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (surveyRepository.existsById(id))
            surveyRepository.deleteById(id);
        else throw new SurveyNotFoundException();
    }
}
