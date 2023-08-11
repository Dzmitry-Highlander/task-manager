package by.itacademy.jd.task_service.dao.api;

import by.itacademy.jd.task_service.dao.entity.Task;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface ITaskRepository extends ListCrudRepository<Task, UUID> {
}
