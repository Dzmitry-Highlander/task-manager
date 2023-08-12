package by.itacademy.jd2.user_service.service.feign;

import by.itacademy.jd2.base_package.core.dto.AuditCreateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

//TODO refactor connection to audit-service
@FeignClient(value = "audit-service", url = "http://audit-service:8080/users/me")
public interface IAuditServiceClient {

    @GetMapping
    void send(AuditCreateDTO auditCreateDTO);
}
