package by.itacademy.jd2.task_service.service.api;

import by.itacademy.jd2.task_service.core.dto.create.TaskCreationDTO;
import by.itacademy.jd2.task_service.core.dto.filter.FilterDTO;
import by.itacademy.jd2.task_service.core.dto.update.TaskUpdateDTO;
import by.itacademy.jd2.task_service.core.enums.ETaskStatus;
import by.itacademy.jd2.task_service.repository.entity.Task;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ITaskService {
    Task create(TaskCreationDTO taskCreationDTO);

    Page<Task> read(
            int page,
            int size,
            FilterDTO filterDTO
    );

    Task read(UUID uuid);

    Task update(UUID uuid, String version, TaskUpdateDTO taskUpdateDTOsk);

    Task patch(UUID uuid, String version, ETaskStatus status);
}
