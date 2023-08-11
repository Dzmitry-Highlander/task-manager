package by.itacademy.jd.task_service.dao.api;

import by.itacademy.jd.task_service.dao.entity.Project;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface IProjectRepository extends ListCrudRepository<Project, UUID> {
}
