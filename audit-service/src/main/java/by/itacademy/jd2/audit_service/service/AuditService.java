package by.itacademy.jd2.audit_service.service;

import by.itacademy.jd2.audit_service.dao.api.IAuditRepository;
import by.itacademy.jd2.audit_service.dao.entity.Audit;
import by.itacademy.jd2.audit_service.service.api.IAuditService;
import by.itacademy.jd2.audit_service.service.exception.AuditNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuditService implements IAuditService {
    private static final String AUDIT_NOT_FOUND_ERROR = "Заданный аудит не найден";

    private final IAuditRepository auditRepository;

    @Transactional(readOnly = true)
    @Override
    public Page<Audit> read(PageRequest pageRequest) {
        return auditRepository.findAll(pageRequest);
    }

    @Transactional(readOnly = true)
    @Override
    public Audit read(UUID uuid) {
        return this.auditRepository.findById(uuid)
                .orElseThrow(() -> new AuditNotFoundException(AUDIT_NOT_FOUND_ERROR + uuid));
    }
}
