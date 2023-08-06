package by.itacademy.jd2.audit_service.service.api;

import by.itacademy.jd2.audit_service.core.dto.AuditCreateDTO;
import by.itacademy.jd2.audit_service.dao.entity.Audit;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface IAuditService {
    Audit create(AuditCreateDTO item);

    Page<Audit> read(int page, int size);

    Audit read(UUID uuid);
}
