package by.itacademy.jd2.task_service.repository.api;

import by.itacademy.jd2.task_service.repository.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ITaskRepository extends JpaRepository<Task, UUID>, JpaSpecificationExecutor<Task> {
}
