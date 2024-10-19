package com.backend.config;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtUtils {

    //caracteristicas propias que debemos poner al token
    @Value("${security.jwt.key.private}")
    private String privateKey;

    @Value("${security.jwt.user.generator}")
    private String userGenerator;

    //hacemos una funcion que se encargue de la creacion del token
    public String createToken(Authentication authentication){

        Algorithm algorithm = Algorithm.HMAC256(this.privateKey);

        String username = authentication.getPrincipal().toString();

        String rol = authentication.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.joining(","));
        
        String token = JWT.create()
        .withIssuer(userGenerator)
        .withSubject(username)
        .withClaim("rol", rol)
        .withIssuedAt(new Date())
        .withExpiresAt(new Date(System.currentTimeMillis() + 1800000))
        .withJWTId(UUID.randomUUID().toString())
        .withNotBefore(new Date(System.currentTimeMillis()))
        .sign(algorithm);

        return token;

    }

    //funcion para decodificar el token
    public DecodedJWT validateToken(String token){
    
    
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.privateKey);
            JWTVerifier verifier = JWT.require(algorithm)
                // specify any specific claim validations
                .withIssuer(userGenerator)
                // reusable verifier instance
                .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT;
        } catch (JWTVerificationException exception){
            throw new JWTVerificationException("Token invalido");
        }
    }

    //extraemos el usuario
    public String extractUsuario(DecodedJWT decodedJWT){
        return decodedJWT.getSubject().toString();
    }
    //extraemos un claim en especifico
    public Claim getEspecificClaim(DecodedJWT decodedJWT, String claimName){
        return decodedJWT.getClaim(claimName);
    }
}
