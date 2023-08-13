package by.itacademy.jd2.user_service.service.feign;

import by.itacademy.jd2.base_package.core.dto.AuditCreateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//TODO refactor connection to audit-service
@FeignClient(name = "audit-service", url = "http://audit-service:8080/internal/audit")
public interface IAuditServiceClient {
    @PostMapping
    void send(@RequestBody AuditCreateDTO auditCreateDTO);
}
