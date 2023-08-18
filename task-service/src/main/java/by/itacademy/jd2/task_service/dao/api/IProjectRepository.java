package by.itacademy.jd2.task_service.dao.api;

import by.itacademy.jd2.task_service.core.enums.EProjectStatus;
import by.itacademy.jd2.task_service.dao.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IProjectRepository extends JpaRepository<Project, UUID> {
    Page<Project> findAllByStatus(PageRequest pageRequest, EProjectStatus status);

    boolean existsByUuidAndManagerUuidOrUuidAndStaffUuid(
            UUID project, UUID manager,
            UUID project2, UUID staff);

    Optional<Project> findByUuidAndManagerUuidOrUuidAndStaffUuid(
            UUID project, UUID manager,
            UUID project2, UUID staff);

    List<Project> findByUuidInAndManagerUuidOrUuidInAndStaffUuid(
            List<UUID> projects, UUID manager,
            List<UUID> project2, UUID staff);

    Page<Project> findByManagerUuidOrStaffUuid(
            UUID manager, UUID staff,
            Pageable pageable);

    List<Project> findByManagerUuidOrStaffUuid(
            UUID manager, UUID staff);

    Page<Project> findByStatusAndManagerUuidOrStatusOrStaffUuid(
            EProjectStatus status, UUID manager,
            EProjectStatus statusN, UUID staff,
            Pageable pageable
    );
}
