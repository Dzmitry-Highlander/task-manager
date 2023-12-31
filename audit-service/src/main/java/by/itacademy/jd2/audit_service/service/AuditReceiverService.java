package by.itacademy.jd2.audit_service.service;

import by.itacademy.jd2.base_package.core.dto.AuditCreateDTO;
import by.itacademy.jd2.audit_service.dao.api.IAuditRepository;
import by.itacademy.jd2.audit_service.dao.entity.Audit;
import by.itacademy.jd2.audit_service.service.api.IAuditReceiverService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuditReceiverService implements IAuditReceiverService {
    private final ConversionService conversionService;
    private final IAuditRepository auditRepository;

    @Transactional
    @Override
    public Audit save(AuditCreateDTO item) {
        return auditRepository.save(Objects.requireNonNull(conversionService.convert(item, Audit.class)));
    }
}
