package by.itacademy.jd2.task_service.service;

import by.itacademy.jd2.base_package.core.dto.UserShortDTO;
import by.itacademy.jd2.base_package.core.enums.EEssenceType;
import by.itacademy.jd2.base_package.core.enums.EUserRole;
import by.itacademy.jd2.task_service.core.dto.FilterDTO;
import by.itacademy.jd2.task_service.core.dto.TaskCreateMyDTO;
import by.itacademy.jd2.task_service.core.dto.TaskUpdateDTO;
import by.itacademy.jd2.task_service.core.enums.ETaskStatus;
import by.itacademy.jd2.task_service.dao.api.ITaskRepository;
import by.itacademy.jd2.task_service.dao.entity.Project;
import by.itacademy.jd2.task_service.dao.entity.Task;
import by.itacademy.jd2.task_service.service.api.IAuditService;
import by.itacademy.jd2.task_service.service.api.IProjectService;
import by.itacademy.jd2.task_service.service.api.ITaskService;
import by.itacademy.jd2.task_service.service.api.IUserHolder;
import by.itacademy.jd2.task_service.service.exception.ItemNotFoundException;
import by.itacademy.jd2.task_service.service.exception.VersionsNotMatchException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService implements ITaskService {
    private static final String INCORRECT_DATA_ERROR = "Введенные данные некорректны";
    private static final String TASK_NOT_FOUND_ERROR = "Проект с таким uuid не найден";
    private static final String ACCESS_DENIED_ERROR = "Доступ запрещен";
    private static final String VERSIONS_NOT_MATCH_ERROR = "Версии не совпадают";

    private final IProjectService projectService;
    private final ITaskRepository taskRepository;
    private final IAuditService auditService;
    private final IUserHolder userHolder;
    private final ConversionService conversionService;

    @Transactional
    @Override
    public Task create(TaskCreateMyDTO item) {
        if (!projectService.ifExist(item.getProject().getUuid(), item.getImplementer().getUuid())) {
            throw new IllegalArgumentException(INCORRECT_DATA_ERROR);
        }

        Task entity = taskRepository.save(Objects.requireNonNull(
                conversionService.convert(item, Task.class))
        );

        UserShortDTO userShortDTO = userHolder.getUser();

        return entity;
    }

    @Override
    public List<Task> read() {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public Task read(UUID uuid) {
        UserShortDTO userDTO = userHolder.getUser();

        if(userDTO.getRole().equals(EUserRole.ROLE_ADMIN)) {
            return taskRepository.findById(uuid)
                    .orElseThrow(() -> new ItemNotFoundException(TASK_NOT_FOUND_ERROR));
        } else {
            List<Project> projectEntities = projectService.readAllByUser(userDTO.getUuid());
            List<UUID> projectsUuid = (projectEntities.stream().map(Project::getUuid).toList());

            return taskRepository.findByUuidAndProjectIn(uuid, projectsUuid)
                    .orElseThrow(() -> new IllegalAccessError(ACCESS_DENIED_ERROR));
        }
    }

    @Transactional
    @Override
    public Task update(UUID uuid, LocalDateTime version, TaskUpdateDTO item) {
        Task task = taskRepository.findById(uuid)
                .orElseThrow(() -> new ItemNotFoundException(TASK_NOT_FOUND_ERROR));

        if (!version.isEqual(task.getUpdateDate())) {
            throw new VersionsNotMatchException(VERSIONS_NOT_MATCH_ERROR);
        }

        task.setTitle(item.getTitle());
        task.setDescription(item.getDescription());
        task.setProject(item.getProject().getUuid());
        task.setImplementer(item.getImplementer().getUuid());
        task.setStatus(item.getStatus());

        UserShortDTO userShortDTO = userHolder.getUser();

        return taskRepository.save(task);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Task> read(int page, int size, FilterDTO filterDTO) {
        UserShortDTO userDTO = userHolder.getUser();
        PageRequest pageRequest = PageRequest.of(page, size);

        if (filterDTO.getStatus() == null) {
            filterDTO.setStatus(List.of(ETaskStatus.values()));
        }

        if (filterDTO.getProject() == null && filterDTO.getImplementer() == null) {
            return taskRepository.findAll(pageRequest);
        } else if(filterDTO.getProject() == null) {
            return taskRepository.findByImplementerIn(filterDTO.getImplementer(), pageRequest);
        } else if(filterDTO.getImplementer() == null) {
            return taskRepository.findByProjectIn(filterDTO.getProject(), pageRequest);
        } else {
            return taskRepository.findByProjectInAndStatusInAndImplementerIn(
                    filterDTO.getProject(),
                    filterDTO.getStatus(),
                    filterDTO.getImplementer(),
                    pageRequest
            );
        }
    }

    @Transactional
    @Override
    public Task updateStatus(UUID uuid, LocalDateTime version, ETaskStatus item) {
        Task taskEntity = taskRepository.findById(uuid)
                .orElseThrow(() -> new ItemNotFoundException(TASK_NOT_FOUND_ERROR));

        return taskEntity;
    }
}
