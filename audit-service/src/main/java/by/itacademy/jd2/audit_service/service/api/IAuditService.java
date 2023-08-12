package by.itacademy.jd2.audit_service.service.api;

import by.itacademy.jd2.audit_service.dao.entity.Audit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface IAuditService {
    Page<Audit> read(PageRequest pageRequest);

    Audit read(UUID uuid);
}
