package ru.rutmiit.user_survey_api.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JwtProvider {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.subject}")
    private String subject;
    @Value("${jwt.claim}")
    private String claim;
    @Value("${jwt.expirationTimeSec}")
    private long expirationTimeSec;

    public String generateToken(String username) {
        return JWT.create()
                .withSubject(subject)
                .withClaim(claim, username)
                .withIssuedAt(new Date())
                .withIssuer(issuer)
                .withExpiresAt(ZonedDateTime.now().plusSeconds(expirationTimeSec).toInstant())
                .sign(Algorithm.HMAC256(secret));
    }

    public String validateTokenAndRetrieveClaim(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT
                .require(Algorithm.HMAC256(secret))
                .withSubject(subject)
                .withIssuer(issuer)
                .build();

        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getClaim(claim).asString();
    }
}