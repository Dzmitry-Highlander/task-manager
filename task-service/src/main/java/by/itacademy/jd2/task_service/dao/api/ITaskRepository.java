package by.itacademy.jd2.task_service.dao.api;

import by.itacademy.jd2.task_service.core.enums.ETaskStatus;
import by.itacademy.jd2.task_service.dao.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ITaskRepository extends JpaRepository<Task, UUID>, JpaSpecificationExecutor<Task> {
    Optional<Task> findByUuidAndProjectIn(
            UUID uuid, List<UUID> projects
    );

    Page<Task> findByProjectInAndStatusInAndImplementerIn(
            List<UUID> projects,
            List<ETaskStatus> statuses,
            List<UUID> implementers,
            Pageable pageable
    );

    Page<Task> findByProjectIn(
            List<UUID> projects,
            Pageable pageable
    );

    Page<Task> findByImplementerIn(
            List<UUID> implementers,
            Pageable pageable
    );
}
