package com.demo.jwt.backend.provider;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.demo.jwt.backend.dto.UserDto;
import com.demo.jwt.backend.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserAuthProvider{

    @Value("${security.jwt.token.secret-ket:secret-key}")
    private String secretKey;

    @Autowired
    UserService userService;
    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String login){
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3_600_000);
        return JWT.create().withIssuer(login).withIssuedAt(now).withExpiresAt(validity).sign(Algorithm.HMAC256(secretKey));
    }

    public  Authentication validateToken(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
        DecodedJWT decodedJWT = verifier.verify(token);

        UserDto user = userService.findByLogin(decodedJWT.getIssuer());

        return new UsernamePasswordAuthenticationToken(user,null, Collections.emptyList());

    }

}
