package by.itacademy.jd.task_service.service;

import by.itacademy.jd.task_service.core.dto.TaskSaveDTO;
import by.itacademy.jd.task_service.dao.api.ITaskRepository;
import by.itacademy.jd.task_service.dao.entity.Task;
import by.itacademy.jd.task_service.service.api.ITaskService;
import by.itacademy.jd.task_service.service.exception.ItemNotFoundException;
import by.itacademy.jd.task_service.service.exception.VersionsNotMatchException;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TaskService implements ITaskService {
    private static final String TASK_NOT_FOUND_ERROR = "Задача с таким uuid не найдена";
    private static final String VERSIONS_NOT_MATCH_ERROR = "Версии не совпадают";

    private final ITaskRepository taskRepository;
    private final ConversionService conversionService;

    @Transactional
    @Override
    public Task create(TaskSaveDTO item) {
        return taskRepository.save(Objects.requireNonNull(conversionService.convert(item, Task.class)));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Task> read() {
        return taskRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Task read(UUID uuid) {
        return taskRepository.findById(uuid)
                .orElseThrow(() -> new ItemNotFoundException(TASK_NOT_FOUND_ERROR));
    }

    @Transactional
    @Override
    public Task update(UUID uuid, Long version, TaskSaveDTO item) {
        Task task = taskRepository.findById(uuid)
                .orElseThrow(() -> new ItemNotFoundException(TASK_NOT_FOUND_ERROR));

        if (!version.equals(task.getUpdateDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())) {
            throw new VersionsNotMatchException(VERSIONS_NOT_MATCH_ERROR);
        }

        task.setTitle(item.getTitle());
        task.setDescription(item.getDescription());
        task.setImplementer(item.getImplementer().getUuid());
        task.setProject(item.getProject().getUuid());
        task.setStatus(item.getStatus());

        return taskRepository.save(task);
    }
}
