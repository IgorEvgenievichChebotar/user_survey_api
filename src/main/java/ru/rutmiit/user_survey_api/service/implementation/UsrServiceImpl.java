package ru.rutmiit.user_survey_api.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rutmiit.user_survey_api.model.Usr;
import ru.rutmiit.user_survey_api.repository.UsrRepository;
import ru.rutmiit.user_survey_api.service.UsrService;

import static ru.rutmiit.user_survey_api.mapper.FieldMapper.mapNonNullFields;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UsrServiceImpl implements UsrService {
    private final UsrRepository usrRepository;

    @Override
    public Usr update(Usr usr) {
        var user = usrRepository.findById(usr.getId()).orElse(new Usr());
        mapNonNullFields(usr, user);
        return usrRepository.save(user);
    }
}
