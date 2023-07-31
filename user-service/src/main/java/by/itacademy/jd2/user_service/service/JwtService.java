package by.itacademy.jd2.user_service.service;

import by.itacademy.jd2.user_service.config.properties.JWTProperty;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class JwtService {
    private JWTProperty property;

    public String extractUserEmail(String jwt) {
        return null;
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(property.getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
