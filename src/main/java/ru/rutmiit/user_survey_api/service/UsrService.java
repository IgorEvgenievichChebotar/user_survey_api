package ru.rutmiit.user_survey_api.service;

import ru.rutmiit.user_survey_api.model.Usr;

public interface UsrService {
    Usr findByEmail(String email);

    Usr findById(Long id);

    Usr update(Usr usr);
}
