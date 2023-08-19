package by.itacademy.jd2.task_service.repository.api;

import by.itacademy.jd2.task_service.repository.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IProjectRepository extends JpaRepository<Project, UUID> {
}
