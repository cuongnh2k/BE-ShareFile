package com.example.besharefile.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.besharefile.dto.produces.LoginProduceDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class TokenConfig {

    @Value("${jwt_secret}")
    private String JWT_SECRET;

    @Value("${jwt_lifetime_access_token}")
    private Long JWT_LIFETIME_ACCESS_TOKEN;

    @Value("${jwt_lifetime_refresh_token}")
    private Long JWT_LIFETIME_REFRESH_TOKEN;

    public LoginProduceDto generateToken(UserDetails userDetails, HttpServletRequest request) {
        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET.getBytes());
        return LoginProduceDto.builder()
                .accessToken(JWT.create()
                        .withSubject(userDetails.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + JWT_LIFETIME_ACCESS_TOKEN))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim(
                                "roles",
                                userDetails.getAuthorities().stream()
                                        .map(GrantedAuthority::getAuthority)
                                        .collect(Collectors.toList()))
                        .sign(algorithm))
                .refreshToken(JWT.create()
                        .withSubject(userDetails.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + JWT_LIFETIME_REFRESH_TOKEN))
                        .withIssuer(request.getRequestURL().toString())
                        .sign(algorithm))
                .build();
    }
}
