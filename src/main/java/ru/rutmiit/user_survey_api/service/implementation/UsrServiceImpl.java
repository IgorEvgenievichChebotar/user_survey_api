package ru.rutmiit.user_survey_api.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rutmiit.user_survey_api.repository.UsrRepository;
import ru.rutmiit.user_survey_api.service.UsrService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UsrServiceImpl implements UsrService {
    private final UsrRepository usrRepository;
}
