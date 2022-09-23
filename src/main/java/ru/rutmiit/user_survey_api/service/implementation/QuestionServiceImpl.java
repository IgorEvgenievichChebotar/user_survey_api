package ru.rutmiit.user_survey_api.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rutmiit.user_survey_api.exception.QuestionNotFoundException;
import ru.rutmiit.user_survey_api.exception.SurveyNotFoundException;
import ru.rutmiit.user_survey_api.model.Question;
import ru.rutmiit.user_survey_api.repository.QuestionRepository;
import ru.rutmiit.user_survey_api.repository.SurveyRepository;
import ru.rutmiit.user_survey_api.service.QuestionService;

import java.util.List;

import static ru.rutmiit.user_survey_api.mapper.ReflectionFieldMapper.mapNonNullFields;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final SurveyRepository surveyRepository;

    @Override
    public Question findById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(QuestionNotFoundException::new);
    }

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    @Transactional
    public Question create(Question question, Long surveyId) {
        question.setSurvey(surveyRepository.findById(surveyId)
                .orElseThrow(SurveyNotFoundException::new));

        return questionRepository.save(question);
    }

    @Override
    @Transactional
    public Question update(Question question, Long id) {
        var foundedQuestion = this.findById(id);

        mapNonNullFields(question, foundedQuestion);
        var options = question.getOptions();
        var foundedOptions = foundedQuestion.getOptions();
        for (int i = 0; i < options.size(); i++){
            var option = options.get(i);
            var foundedOption = foundedOptions.get(i);
            mapNonNullFields(option, foundedOption);
        }

        return questionRepository.save(foundedQuestion);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        questionRepository.deleteById(id);
    }
}
