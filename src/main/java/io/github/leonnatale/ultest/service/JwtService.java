package io.github.leonnatale.ultest.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.stereotype.Service;

import java.time.*;

@Service
public class JwtService {
    private String SALT = System.getenv("SECRET_SALT");

    private String ISSUER = System.getenv("SECRET_ISSUER");

    private Algorithm algorithm;

    public JwtService() {
        algorithm = Algorithm.HMAC256(SALT);
    }

    public String generate(Long id) {
        return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(id.toString())
                .withExpiresAt(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).plusHours(1).toInstant())
                .sign(algorithm);
    }

    public String getSubjectFromToken(String jwt)
        throws SignatureVerificationException, TokenExpiredException
    {
        return JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build()
                .verify(jwt)
                .getSubject();

    }
}
