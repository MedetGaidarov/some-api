package com.example.backendjavaapijob.infrastructure.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String signingKey;

    @Value("${jwt.issuer}")
    private String jwtIssuer;

    @Value("${jwt.refresh-secret}")
    private String refreshSigningKey;

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature - {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token - {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token - {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token - {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT token string is empty or corrupted any other way - {}", e.getMessage());
        }
        return false;
    }

    public String getEmail(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(token)
                .getBody();
        return claims.get("email", String.class);
    }

    public String getRefreshEmail(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(refreshSigningKey)
                .parseClaimsJws(token)
                .getBody();
        return claims.get("email", String.class);
    }

    public String getRole(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(token)
                .getBody();
        return claims.get("role", String.class);
    }

    public String getRefreshRole(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(refreshSigningKey)
                .parseClaimsJws(token)
                .getBody();
        return claims.get("role", String.class);
    }

    public String generateAccessToken(String username, Collection<? extends GrantedAuthority> authorities) {
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + 40 * 60 * 1000))
                .withIssuer(jwtIssuer)
                .withClaim("roles", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);

    }

    public String generateRefreshToken(String username) {
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .withIssuer(jwtIssuer)
                .sign(algorithm);

    }

}


