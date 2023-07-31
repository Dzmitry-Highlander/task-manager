package by.itacademy.jd2.user_service.service;

import by.itacademy.jd2.user_service.config.properties.JWTProperty;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    private JWTProperty property;

    public String extractUserEmail(String jwt) {
        return null;
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(property.getSecret())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
