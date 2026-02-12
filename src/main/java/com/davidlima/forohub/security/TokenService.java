package com.davidlima.forohub.security;

import com.davidlima.forohub.domain.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expiration}")
  private Long expirationMillis;

  public String generarToken(Authentication authentication) {
    Usuario usuario = (Usuario) authentication.getPrincipal();
    Algorithm algorithm = Algorithm.HMAC256(secret);

    Instant expiracion = LocalDateTime.now()
            .plusSeconds(expirationMillis / 1000)
            .toInstant(ZoneOffset.of("-03:00"));

    return JWT.create()
            .withIssuer("forohub-api")
            .withSubject(usuario.getUsername())
            .withExpiresAt(expiracion)
            .sign(algorithm);
  }

  public String getSubject(String tokenJWT) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      return JWT.require(algorithm)
              .withIssuer("forohub-api")
              .build()
              .verify(tokenJWT)
              .getSubject();
    } catch (JWTVerificationException ex) {
      return null;
    }
  }
}
