package by.itacademy.jd2.task_service.service.feign;

import by.itacademy.jd2.base_package.core.dto.AuditCreateDTO;
import by.itacademy.jd2.base_package.core.dto.AuditDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@FeignClient(name = "audit-service", url = "http://audit-service:8080")
public interface IAuditFeignClient {
    @PostMapping("/internal/audit")
    ResponseEntity<?> send(@RequestBody AuditCreateDTO auditCreateDTO);

    @GetMapping("/internal/audit/find")
    ResponseEntity<List<AuditDTO>> getByParam(
            @RequestParam UUID uuid,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    );
}

