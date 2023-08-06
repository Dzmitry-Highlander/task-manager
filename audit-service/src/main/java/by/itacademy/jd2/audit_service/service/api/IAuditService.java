package by.itacademy.jd2.audit_service.service.api;

import by.itacademy.jd2.audit_service.core.dto.AuditCreateDTO;
import by.itacademy.jd2.audit_service.dao.entity.Audit;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface IAuditService {
    Audit save(AuditCreateDTO item);

    Page<Audit> get(int page, int size);

    Audit get(UUID uuid);
}
