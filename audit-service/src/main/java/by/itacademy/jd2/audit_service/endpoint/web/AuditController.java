package by.itacademy.jd2.audit_service.endpoint.web;

import by.itacademy.jd2.audit_service.core.dto.AuditDTO;
import by.itacademy.jd2.audit_service.service.api.IAuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/audit")
@RequiredArgsConstructor
public class AuditController {
    private final IAuditService auditService;
    private final ConversionService conversionService;

    @GetMapping("/audit")
    public ResponseEntity<PageDTO<AuditDTO>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Page<Audit> audits = this.auditService.get(page, size);
        PageDTO<AuditDTO> pageDTO = PageToPageDTOUtil.convert(audits, conversionService);

        return new ResponseEntity<>(pageDTO, HttpStatus.OK);
    }
}
