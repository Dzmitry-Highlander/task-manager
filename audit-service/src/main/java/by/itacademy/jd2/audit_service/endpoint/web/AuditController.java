package by.itacademy.jd2.audit_service.endpoint.web;

import by.itacademy.jd.base_pakage.core.dto.PageDTO;
import by.itacademy.jd2.audit_service.core.dto.AuditDTO;
import by.itacademy.jd2.audit_service.service.api.IAuditService;
import by.itacademy.jd2.audit_service.service.converter.PageToPageDTOConverter;
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
        PageDTO<AuditDTO> pageDTO = PageToPageDTOConverter.convert(auditService.get(page, size), conversionService);

        return new ResponseEntity<>(pageDTO, HttpStatus.OK);
    }

    @GetMapping("/audit/{uuid}")
    public ResponseEntity<AuditDTO> findById(@PathVariable UUID uuid){
        AuditDTO dto = conversionService.convert(auditService.get(uuid), AuditDTO.class);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
