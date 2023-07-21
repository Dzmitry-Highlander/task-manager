package by.itacademy.jd2.comments_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories(basePackages = "by.itacademy.jd2.comments_service.dao.api")
@EnableTransactionManagement
@SpringBootApplication
public class CommentsServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(CommentsServiceApplication.class, args);
	}
}
