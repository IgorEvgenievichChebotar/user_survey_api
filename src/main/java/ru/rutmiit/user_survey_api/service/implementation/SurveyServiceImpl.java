package ru.rutmiit.user_survey_api.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rutmiit.user_survey_api.exception.SurveyNotFoundException;
import ru.rutmiit.user_survey_api.model.Survey;
import ru.rutmiit.user_survey_api.repository.SurveyRepository;
import ru.rutmiit.user_survey_api.service.SurveyService;

import java.util.List;

import static java.time.LocalDateTime.now;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SurveyServiceImpl implements SurveyService {
    private final SurveyRepository surveyRepository;

    @Override
    public Survey findById(Long id) {
        return surveyRepository.findById(id)
                .orElseThrow(() -> new SurveyNotFoundException("survey with this id was not found"));
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
        return surveyRepository.save(survey);
    }

    @Override
    @Transactional
    public Survey update(Survey survey, Long id) {
        if (surveyRepository.existsById(id))
            return surveyRepository.save(survey);
        else throw new SurveyNotFoundException("survey with this id was not found");
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (surveyRepository.existsById(id))
            surveyRepository.deleteById(id);
        else throw new SurveyNotFoundException("survey with this id was not found");
    }
}
