package by.itacademy.jd2.task_service;

import by.itacademy.jd2.task_service.config.property.JWTProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableConfigurationProperties({JWTProperty.class})
@EnableJpaRepositories(basePackages = "by.itacademy.jd2.task_service.dao.api")
@EnableTransactionManagement
@EnableFeignClients
@SpringBootApplication
public class TaskServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(TaskServiceApplication.class, args);
	}
}
