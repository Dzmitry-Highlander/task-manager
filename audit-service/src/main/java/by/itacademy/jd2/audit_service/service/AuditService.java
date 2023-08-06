package by.itacademy.jd2.audit_service.service;

import by.itacademy.jd2.audit_service.core.dto.AuditCreateDTO;
import by.itacademy.jd2.audit_service.dao.entity.Audit;
import by.itacademy.jd2.audit_service.service.api.IAuditService;
import org.springframework.data.domain.Page;

import java.util.UUID;

public class AuditService implements IAuditService {
    @Override
    public Audit save(AuditCreateDTO item) {
        return null;
    }

    @Override
    public Page<Audit> get(int page, int size) {
        return null;
    }

    @Override
    public Audit get(UUID uuid) {
        return null;
    }
}
