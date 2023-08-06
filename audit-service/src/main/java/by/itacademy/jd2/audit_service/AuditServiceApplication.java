package by.itacademy.jd2.audit_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableConfigurationProperties({})
@EnableJpaRepositories(basePackages = "by.itacademy.jd2.audit_service.dao.api")
@EnableTransactionManagement
@EnableFeignClients
@SpringBootApplication
public class AuditServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(AuditServiceApplication.class, args);
	}
}
