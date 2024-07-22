package com.estacionamento.estacionamento_api.infra;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.estacionamento.estacionamento_api.usuarios.Usuario;

@Service
public class ServicoToken {

    @Value("${api.security.token.secret}")
    private String secret;
    
    public String criarToken (Usuario usuario) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                      .withIssuer("Estacionamento_api")
                      .withSubject(usuario.getLogin())
                      .withExpiresAt(dataExpiração())
                      .sign(algorithm);
            } catch (JWTCreationException exception){
                throw new RuntimeException("Erro ao gerar token", exception);
        }
    }

    public String getSubject (String tokenJWT) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                .withIssuer("Estacionamento_api")
                .build()
                .verify(tokenJWT)
                .getSubject();
        
        } catch (JWTVerificationException exception){
            throw new RuntimeException("token inválido ou expirado");
        }
    }

    private Instant dataExpiração() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
