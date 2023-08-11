package by.itacademy.jd2.task_service.service.api;

import by.itacademy.jd2.task_service.core.dto.TaskSaveDTO;
import by.itacademy.jd2.task_service.dao.entity.Task;

public interface ITaskService extends ICRUDService<Task, TaskSaveDTO> {
}
