package by.itacademy.jd2.task_service.dao.api;

import by.itacademy.jd2.task_service.core.enums.EProjectStatus;
import by.itacademy.jd2.task_service.dao.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.UUID;

public interface IProjectRepository extends JpaRepository<Project, UUID> {
    Page<Project> findAllByStatus(PageRequest pageRequest, EProjectStatus status);
}
