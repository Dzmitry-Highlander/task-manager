package by.itacademy.jd2.user_service.config.properties;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.Key;

@Setter
@ConfigurationProperties(prefix = "jwt")
public class JWTProperty {
    private String key;

    public Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(key);

        return Keys.hmacShaKeyFor(keyBytes);
    }
}
