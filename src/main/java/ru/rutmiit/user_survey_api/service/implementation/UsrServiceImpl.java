package ru.rutmiit.user_survey_api.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rutmiit.user_survey_api.exception.UserNotFoundException;
import ru.rutmiit.user_survey_api.model.Usr;
import ru.rutmiit.user_survey_api.repository.UsrRepository;
import ru.rutmiit.user_survey_api.service.UsrService;

import java.util.Optional;

import static ru.rutmiit.user_survey_api.mapper.FieldMapper.mapNonNullFields;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UsrServiceImpl implements UsrService {
    private final UsrRepository usrRepository;

    @Override
    public Usr findById(Long id) {
        return usrRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    @Transactional
    public Usr saveOrUpdate(Usr usr) {
        if (usr != null && usr.getEmail() != null) {
            Optional<Usr> uzr = usrRepository.findByEmail(usr.getEmail());
            if (uzr.isPresent()) {
                Usr foundedUser = uzr.get();
                mapNonNullFields(usr, foundedUser);
                return usrRepository.save(foundedUser);
            } else
                return usrRepository.save(usr);
        } else
            return usrRepository.save(new Usr());
    }
}
