package vn.edu.cuongnh2k.be_realtime.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import vn.edu.cuongnh2k.be_realtime.dto.produces.TokenProduceDto;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class TokenConfig {

    @Value("${jwt_secret}")
    private String JWT_SECRET;

    @Value("${jwt_access_token_validity_period}")
    private Long JWT_ACCESS_TOKEN_VALIDITY_PERIOD;

    @Value("${jwt_refresh_token_validity_period}")
    private Long JWT_REFRESH_TOKEN_VALIDITY_PERIOD;

    public TokenProduceDto generateToken(UserDetails userDetails, HttpServletRequest request) {
        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET.getBytes());
        return TokenProduceDto.builder()
                .accessToken(JWT.create()
                        .withSubject(userDetails.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + JWT_ACCESS_TOKEN_VALIDITY_PERIOD))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim(
                                "roles",
                                userDetails.getAuthorities().stream()
                                        .map(GrantedAuthority::getAuthority)
                                        .collect(Collectors.toList()))
                        .withClaim("type", "access")
                        .sign(algorithm))
                .refreshToken(JWT.create()
                        .withSubject(userDetails.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + JWT_REFRESH_TOKEN_VALIDITY_PERIOD))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("type", "refresh")
                        .sign(algorithm))
                .build();
    }
}
