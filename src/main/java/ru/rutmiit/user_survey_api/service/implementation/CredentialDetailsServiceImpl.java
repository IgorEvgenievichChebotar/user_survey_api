package ru.rutmiit.user_survey_api.service.implementation;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.rutmiit.user_survey_api.model.Credential;
import ru.rutmiit.user_survey_api.model.enumeration.RoleType;
import ru.rutmiit.user_survey_api.repository.CredentialRepository;
import ru.rutmiit.user_survey_api.service.CredentialService;

import java.util.Collections;

@Service
@AllArgsConstructor
public class CredentialDetailsServiceImpl implements CredentialService, UserDetailsService {
    private final CredentialRepository credentialRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return credentialRepository.findByUsernameIgnoreCase(username)
                .map(c -> new User(
                        c.getUsername(),
                        c.getPassword(),
                        Collections.singleton(c.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("user with this username was not found"));
    }

    @Override
    public void reg(Credential credential) {
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        credential.setRole(RoleType.ADMIN);

        credentialRepository.save(credential);
    }

    @Override
    public boolean existsByUsername(String username) {
        return credentialRepository.existsByUsername(username);
    }
}
