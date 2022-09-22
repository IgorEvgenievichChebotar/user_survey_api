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
/*        var answersWithPk = answers.stream()
                .peek(a -> a.setId(new AnswerId(a.getUsr().getId(), a.getQuestion().getId())))
                .toList();*/
        answerRepository.saveAll(answers);
    }
}
