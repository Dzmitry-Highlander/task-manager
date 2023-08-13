package by.itacademy.jd2.audit_service.service.feign;

import by.itacademy.jd2.base_package.core.dto.AuditCreateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "audit-service")
public interface IAuditServiceClient{
    @PostMapping(value = "/internal/save", consumes = "application/json", produces = "application/json")
    ResponseEntity<?> save(@RequestBody AuditCreateDTO auditCreateDTO);
}
