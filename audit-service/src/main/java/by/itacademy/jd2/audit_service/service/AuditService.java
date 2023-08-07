package by.itacademy.jd2.audit_service.service;

import by.itacademy.jd2.audit_service.core.dto.AuditCreateDTO;
import by.itacademy.jd2.audit_service.dao.api.IAuditRepository;
import by.itacademy.jd2.audit_service.dao.entity.Audit;
import by.itacademy.jd2.audit_service.service.api.IAuditService;
import by.itacademy.jd2.audit_service.service.exception.AuditNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuditService implements IAuditService {
    private static final String AUDIT_NOT_FOUND_ERROR = "Заданный аудит не найден: ";

    private final IAuditRepository auditRepository;
    private final ConversionService conversionService;

    @Transactional
    @Override
    public Audit create(AuditCreateDTO item) {
        return auditRepository.save(Objects.requireNonNull(conversionService.convert(item, Audit.class)));
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Audit> read(int page, int size) {
        return auditRepository.findAll(PageRequest.of(page, size));
    }

    @Transactional(readOnly = true)
    @Override
    public Audit read(UUID uuid) {
        return this.auditRepository.findById(uuid)
                .orElseThrow(() -> new AuditNotFoundException(AUDIT_NOT_FOUND_ERROR + uuid));
    }
}
