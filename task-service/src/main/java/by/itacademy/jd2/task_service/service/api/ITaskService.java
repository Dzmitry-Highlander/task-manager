package by.itacademy.jd2.task_service.service.api;

import by.itacademy.jd2.task_service.core.dto.create.TaskCreationDTO;
import by.itacademy.jd2.task_service.core.dto.filter.ImplementerFilterDTO;
import by.itacademy.jd2.task_service.core.dto.filter.ProjectFilterDTO;
import by.itacademy.jd2.task_service.core.dto.update.TaskUpdateDTO;
import by.itacademy.jd2.task_service.core.enums.ETaskStatus;
import by.itacademy.jd2.task_service.repository.entity.Task;

import java.util.UUID;

public interface ITaskService {
    Task create(TaskCreationDTO taskCreationDTO);

    Task read(
            int page,
            int size,
            ProjectFilterDTO projectFilter,
            ImplementerFilterDTO implementerFilter,
            ETaskStatus status
    );

    Task read(UUID uuid);

    Task update(UUID uuid, String version, TaskUpdateDTO taskUpdateDTOsk);

    Task patch(UUID uuid, String version, ETaskStatus status);
}
