package ru.rutmiit.user_survey_api.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rutmiit.user_survey_api.model.Answer;
import ru.rutmiit.user_survey_api.repository.AnswerRepository;
import ru.rutmiit.user_survey_api.service.AnswerService;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;

    @Override
    @Transactional
    public void commitAll(List<Answer> answers) {
        answerRepository.saveAll(answers);
    }

    @Override
    public List<Answer> findByUserId(Long id) {
        return answerRepository.findByUserId(id);
    }
}
