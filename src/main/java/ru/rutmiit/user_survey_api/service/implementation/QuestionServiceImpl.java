package ru.rutmiit.user_survey_api.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rutmiit.user_survey_api.model.Question;
import ru.rutmiit.user_survey_api.repository.QuestionRepository;
import ru.rutmiit.user_survey_api.service.QuestionService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    @Override
    @Transactional
    public Question create(Question question, Long surveyId) {
        return null;
    }

    @Override
    @Transactional
    public Question update(Question question, Long id) {
        return null;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {

    }
}
