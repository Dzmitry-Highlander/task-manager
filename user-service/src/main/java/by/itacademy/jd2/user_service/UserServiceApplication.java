package by.itacademy.jd2.user_service;

import by.itacademy.jd2.user_service.config.properties.JWTProperty;
import by.itacademy.jd2.user_service.config.properties.MailProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableConfigurationProperties({MailProperty.class, JWTProperty.class})
@EnableJpaRepositories(basePackages = "by.itacademy.jd2.user_service.dao.api")
@EnableTransactionManagement
@SpringBootApplication
public class UserServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
}
