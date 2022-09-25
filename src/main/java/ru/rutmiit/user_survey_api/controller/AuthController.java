package ru.rutmiit.user_survey_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rutmiit.user_survey_api.dto.request.auth.CredentialDtoRequest;
import ru.rutmiit.user_survey_api.dto.response.TokenDtoResponse;
import ru.rutmiit.user_survey_api.exception.UserWasNotRegisteredException;
import ru.rutmiit.user_survey_api.mapper.CredentialMapper;
import ru.rutmiit.user_survey_api.security.JwtProvider;
import ru.rutmiit.user_survey_api.service.CredentialService;
import ru.rutmiit.user_survey_api.util.ExceptionMessageBuilder;
import ru.rutmiit.user_survey_api.validation.CredentialValidator;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final JwtProvider jwtProvider;
    private final CredentialService credentialService;
    private final AuthenticationManager authenticationManager;
    private final CredentialValidator credentialValidator;

    @PostMapping("/reg")
    public TokenDtoResponse reg(@RequestBody @Valid CredentialDtoRequest request,
                                BindingResult bindingResult) {

        credentialValidator.validate(request, bindingResult);
        if (bindingResult.hasErrors()) {
            var msg = ExceptionMessageBuilder.buildMessage(bindingResult);
            throw new UserWasNotRegisteredException(msg);
        }

        var credential = CredentialMapper.toCredential(request);

        credentialService.reg(credential);

        var token = jwtProvider.generateToken(credential.getUsername());

        return new TokenDtoResponse(token);
    }

    @PostMapping("/login")
    public TokenDtoResponse login(@RequestBody CredentialDtoRequest request) {

        var authenticationToken = new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword());
        authenticationManager.authenticate(authenticationToken);

        var token = jwtProvider.generateToken(request.getUsername());

        return new TokenDtoResponse(token);
    }
}
