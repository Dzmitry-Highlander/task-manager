package by.itacademy.jd2.task_service.service;

import by.itacademy.jd2.task_service.core.dto.create.TaskCreationDTO;
import by.itacademy.jd2.task_service.core.dto.filter.ImplementerFilterDTO;
import by.itacademy.jd2.task_service.core.dto.filter.ProjectFilterDTO;
import by.itacademy.jd2.task_service.core.dto.update.TaskUpdateDTO;
import by.itacademy.jd2.task_service.core.enums.ETaskStatus;
import by.itacademy.jd2.task_service.core.exception.VersionsNotMatchException;
import by.itacademy.jd2.task_service.repository.api.ITaskRepository;
import by.itacademy.jd2.task_service.repository.entity.Task;
import by.itacademy.jd2.task_service.service.api.ITaskService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService implements ITaskService {
    //TODO Audit
    private static final String TASK_NOT_FOUND_ERROR = "Задача по указанному uuid не найдена";
    private static final String VERSIONS_NOT_MATCH_ERROR = "Версии не совпадают";

    private final ITaskRepository taskRepository;
    private final ConversionService conversionService;

    @Transactional
    @Override
    public Task create(TaskCreationDTO taskCreationDTO) {
        Task task = conversionService.convert(taskCreationDTO, Task.class);

        return taskRepository.saveAndFlush(Objects.requireNonNull(task));
    }

    @Transactional(readOnly = true)
    @Override
    public Task read(
            int page,
            int size,
            ProjectFilterDTO projectFilter,
            ImplementerFilterDTO implementerFilter,
            ETaskStatus status
    ) {
        //TODO закончить метод
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public Task read(UUID uuid) {
        return taskRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException(TASK_NOT_FOUND_ERROR));
    }

    @Transactional
    @Override
    public Task update(UUID uuid, String version, TaskUpdateDTO taskUpdateDTO) {
        LocalDateTime localDateTime = conversionService.convert(version, LocalDateTime.class);
        Task task = taskRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException(TASK_NOT_FOUND_ERROR));

        if (!Objects.equals(localDateTime, task.getCreateDate())) {
            throw new VersionsNotMatchException(VERSIONS_NOT_MATCH_ERROR);
        }

        task.setTitle(taskUpdateDTO.getTitle());
        task.setDescription(taskUpdateDTO.getDescription());
        task.setStatus(taskUpdateDTO.getStatus());
        task.setImplementor(taskUpdateDTO.getImplementer().getUuid());

        return taskRepository.saveAndFlush(task);
    }

    @Transactional
    @Override
    public Task patch(UUID uuid, String version, ETaskStatus status) {
        LocalDateTime localDateTime = conversionService.convert(version, LocalDateTime.class);
        Task task = taskRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException(TASK_NOT_FOUND_ERROR));

        if (!Objects.equals(localDateTime, task.getCreateDate())) {
            throw new VersionsNotMatchException(VERSIONS_NOT_MATCH_ERROR);
        }

        task.setStatus(status);

        return taskRepository.saveAndFlush(task);
    }
}
