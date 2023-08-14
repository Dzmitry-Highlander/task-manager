package by.itacademy.jd2.audit_service.service.api;

import by.itacademy.jd2.base_package.core.dto.AuditCreateDTO;
import by.itacademy.jd2.audit_service.dao.entity.Audit;
import jakarta.validation.Valid;

public interface IAuditReceiverService {
    Audit save(@Valid AuditCreateDTO item);
}
