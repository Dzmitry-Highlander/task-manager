package by.itacademy.jd2.task_service.dao.api;

import by.itacademy.jd2.task_service.dao.entity.Task;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface ITaskRepository extends ListCrudRepository<Task, UUID> {
}
