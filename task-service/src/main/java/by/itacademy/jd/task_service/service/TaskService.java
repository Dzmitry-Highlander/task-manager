package by.itacademy.jd.task_service.service;

import by.itacademy.jd.task_service.core.dto.TaskSaveDTO;
import by.itacademy.jd.task_service.dao.entity.Task;
import by.itacademy.jd.task_service.service.api.ITaskService;

import java.util.List;
import java.util.UUID;

public class TaskService implements ITaskService {
    @Override
    public Task create(TaskSaveDTO item) {
        return null;
    }

    @Override
    public List<Task> read() {
        return null;
    }

    @Override
    public Task read(UUID uuid) {
        return null;
    }

    @Override
    public Task update(UUID uuid, Long version, TaskSaveDTO item) {
        return null;
    }
}
