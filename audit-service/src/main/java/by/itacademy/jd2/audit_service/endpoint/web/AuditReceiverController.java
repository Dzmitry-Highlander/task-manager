package by.itacademy.jd2.audit_service.endpoint.web;

import by.itacademy.jd2.audit_service.service.api.IAuditReceiverService;
import by.itacademy.jd2.base_package.core.dto.AuditCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/internal/audit")
@RequiredArgsConstructor
public class AuditReceiverController {
    private final IAuditReceiverService auditReceiverService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(@RequestBody AuditCreateDTO auditCreateDTO){
        auditReceiverService.save(auditCreateDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
