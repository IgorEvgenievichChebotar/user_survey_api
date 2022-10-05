package ru.rutmiit.user_survey_api.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rutmiit.user_survey_api.exception.QuestionNotFoundException;
import ru.rutmiit.user_survey_api.model.Question;
import ru.rutmiit.user_survey_api.model.Survey;
import ru.rutmiit.user_survey_api.repository.QuestionRepository;
import ru.rutmiit.user_survey_api.service.QuestionService;
import ru.rutmiit.user_survey_api.service.SurveyService;

import java.util.List;

import static ru.rutmiit.user_survey_api.mapper.FieldMapper.mapNonNullFields;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final SurveyService surveyService;

    @Override
    public Question findById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(QuestionNotFoundException::new);
    }

    @Override
    public Question findByText(String text) {
        return questionRepository.findByTextContainsIgnoreCase(text)
                .orElseThrow(QuestionNotFoundException::new);
    }

    @Override
    public List<Question> findAllBySurveyId(Long id) {
        return questionRepository.findAllBySurveyId(id);
    }

    @Override
    public boolean existsByText(String text) {
        return questionRepository.existsByText(text);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN')")
    public Question create(Question question, Long surveyId) {
        question.setSurvey(surveyService.findById(surveyId));

        return questionRepository.save(question);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Question> createAll(List<Question> questions, Long surveyId) {
        Survey foundedSurvey = surveyService.findById(surveyId);

        for (Question q : questions)
            q.setSurvey(foundedSurvey);

        return questionRepository.saveAll(questions);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN')")
    public Question update(Question question, Long id) {
        Question foundedQuestion = this.findById(id);

        mapNonNullFields(question, foundedQuestion);

        return questionRepository.save(foundedQuestion);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteById(Long id) {
        if (questionRepository.existsById(id))
            questionRepository.deleteById(id);
        else throw new QuestionNotFoundException();
    }
}
