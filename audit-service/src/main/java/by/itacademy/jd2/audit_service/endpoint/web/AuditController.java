package by.itacademy.jd2.audit_service.endpoint.web;

import by.itacademy.jd2.base_package.core.dto.PageDTO;
import by.itacademy.jd2.audit_service.core.dto.AuditDTO;
import by.itacademy.jd2.audit_service.service.api.IAuditService;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
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
    public ResponseEntity<?> list(
            @RequestParam(defaultValue = "0") @PositiveOrZero int page,
            @RequestParam(defaultValue = "20") @PositiveOrZero int size) {
        PageDTO<AuditDTO> pageOfAuditDTO = conversionService.convert( //TODO как лучше конвертировать?
                auditService.read(PageRequest.of(page, size)), PageDTO.class
        );

        return new ResponseEntity<>(pageOfAuditDTO, HttpStatus.OK);
    }

    @GetMapping("/audit/{uuid}")
    public ResponseEntity<AuditDTO> findById(@PathVariable UUID uuid) {
        AuditDTO dto = conversionService.convert(auditService.read(uuid), AuditDTO.class);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
