package by.itacademy.jd2.user_service.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.Key;

@Getter
@Setter
@ConfigurationProperties(prefix = "jwt")
public class JWTProperty {
    private Key key;
    private String issuer;
}
