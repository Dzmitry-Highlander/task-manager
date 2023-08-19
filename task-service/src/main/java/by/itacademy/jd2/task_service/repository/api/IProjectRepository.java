package by.itacademy.jd2.task_service.repository.api;

import by.itacademy.jd2.task_service.repository.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IProjectRepository extends JpaRepository<Project, UUID> {
}
