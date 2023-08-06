package by.itacademy.jd2.audit_service.dao.api;

import by.itacademy.jd2.audit_service.dao.entity.Audit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface IAuditRepository extends ListCrudRepository<Audit, UUID> {
    Page<Audit> findAll(Pageable pageable);
}
