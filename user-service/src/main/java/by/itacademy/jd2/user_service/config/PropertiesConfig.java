package by.itacademy.jd2.user_service.config;

import by.itacademy.jd2.user_service.config.properties.JWTProperty;
import by.itacademy.jd2.user_service.config.properties.MailProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({MailProperty.class, JWTProperty.class})
public class PropertiesConfig {
    @Autowired
    private MailProperty mailProperty;

    @Autowired
    private JWTProperty jwtProperty;
}
