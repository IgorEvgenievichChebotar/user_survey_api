package ru.rutmiit.user_survey_api.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rutmiit.user_survey_api.exception.UserNotFoundException;
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
    public Usr findByEmail(String email) {
        return usrRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Usr findById(Long id) {
        return usrRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Usr update(Usr usr) {
        if (usr != null && usr.getEmail() != null) {
            var uzr = usrRepository.findByEmail(usr.getEmail());
            if (uzr.isPresent()) {
                var foundedUser = uzr.get();
                mapNonNullFields(usr, foundedUser);
                return usrRepository.save(foundedUser);
            } else
                return usrRepository.save(usr);
        } else
            return usrRepository.save(new Usr());
    }
}
