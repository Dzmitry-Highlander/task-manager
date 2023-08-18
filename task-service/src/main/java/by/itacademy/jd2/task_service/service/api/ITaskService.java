package by.itacademy.jd2.task_service.service.api;

import by.itacademy.jd2.task_service.core.dto.FilterDTO;
import by.itacademy.jd2.task_service.core.dto.TaskCreateMyDTO;
import by.itacademy.jd2.task_service.core.dto.TaskUpdateDTO;
import by.itacademy.jd2.task_service.core.enums.ETaskStatus;
import by.itacademy.jd2.task_service.dao.entity.Task;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ITaskService extends ICRUDService<Task, TaskCreateMyDTO, TaskUpdateDTO> {
    Page<Task> read(int page, int size, FilterDTO filterDTO);

    Task updateStatus(UUID uuid, LocalDateTime version, ETaskStatus item);
}
