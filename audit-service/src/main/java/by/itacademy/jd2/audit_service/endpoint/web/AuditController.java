package by.itacademy.jd2.audit_service.endpoint.web;

import by.itacademy.jd2.base_package.core.dto.PageDTO;
import by.itacademy.jd2.audit_service.core.dto.AuditDTO;
import by.itacademy.jd2.audit_service.service.api.IAuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/audit")
@RequiredArgsConstructor
public class AuditController {
    private final IAuditService auditService;
    private final ConversionService conversionService;

    @GetMapping
    public ResponseEntity<PageDTO<AuditDTO>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/audit/{uuid}")
    public ResponseEntity<AuditDTO> findById(@PathVariable UUID uuid) {
        AuditDTO dto = conversionService.convert(auditService.read(uuid), AuditDTO.class);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
