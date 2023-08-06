package by.itacademy.jd2.audit_service.dao.api;

import by.itacademy.jd2.audit_service.core.dto.AuditDTO;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface IAuditRepository extends ListCrudRepository<AuditDTO, UUID> {
}
