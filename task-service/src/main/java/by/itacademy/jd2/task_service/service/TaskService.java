package by.itacademy.jd2.task_service.service;

import by.itacademy.jd2.base_package.core.dto.UserShortDTO;
import by.itacademy.jd2.base_package.core.enums.EUserRole;
import by.itacademy.jd2.task_service.core.dto.*;
import by.itacademy.jd2.task_service.core.enums.ETaskStatus;
import by.itacademy.jd2.task_service.core.specification.TaskSpecifications;
import by.itacademy.jd2.task_service.dao.api.ITaskRepository;
import by.itacademy.jd2.task_service.dao.entity.Project;
import by.itacademy.jd2.task_service.dao.entity.Task;
import by.itacademy.jd2.task_service.service.api.IProjectService;
import by.itacademy.jd2.task_service.service.api.ITaskService;
import by.itacademy.jd2.task_service.service.api.IUserService;
import by.itacademy.jd2.task_service.service.exception.ItemNotFoundException;
import by.itacademy.jd2.task_service.service.exception.VersionsNotMatchException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TaskService implements ITaskService {
    private static final String TASK_NOT_FOUND_ERROR = "Задача с таким uuid не найдена";
    private static final String VERSIONS_NOT_MATCH_ERROR = "Версии не совпадают";
    private static final String INCORRECT_DATA_ERROR = "Некорректные данные";

    private final ITaskRepository taskRepository;
    private final IProjectService projectService;
    private final IUserService userService;
    private final ConversionService conversionService;

    @Transactional(readOnly = true)
    @Override
    public Page<Task> read(int page, int size, FilterDTO filterDTO) {
        if (page < 0 || size < 0) {
            throw new IllegalArgumentException(INCORRECT_DATA_ERROR);
        }

        UserShortDTO user = userService.getMe(SecurityContextHolder.getContext().getAuthentication().toString());
        List<ProjectRefDTO> availableProjects = this.getFilteredProjectRefs(user, filterDTO);

        filterDTO.setProject(availableProjects);

        if (filterDTO.getImplementer() == null) {
            filterDTO.setImplementer(List.of(new UserRefDTO(user.getUuid())));
        }

        if (filterDTO.getStatus() == null) {
            filterDTO.setStatus(List.of(
                    ETaskStatus.CLOSE,
                    ETaskStatus.BLOCK,
                    ETaskStatus.WAIT,
                    ETaskStatus.IN_WORK,
                    ETaskStatus.DONE)
            );
        }

        Specification<Task> specification = Specification.where(
                TaskSpecifications.byProject(filterDTO.getProject()))
                .and(TaskSpecifications.byImplementer(filterDTO.getImplementer()))
                .and(TaskSpecifications.byStatus(filterDTO.getStatus())
                );

        return taskRepository.findAll(specification, PageRequest.of(page, size));
    }

    @Transactional
    @Override
    public Task create(TaskCreateMyDTO item) {
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
    public Task update(UUID uuid, LocalDateTime version, TaskUpdateDTO item) {
        Task task = taskRepository.findById(uuid)
                .orElseThrow(() -> new ItemNotFoundException(TASK_NOT_FOUND_ERROR));

        if (!version.equals(task.getUpdateDate())) {
            throw new VersionsNotMatchException(VERSIONS_NOT_MATCH_ERROR);
        }

        task.setTitle(item.getTitle());
        task.setDescription(item.getDescription());
        task.setImplementer(item.getImplementer().getUuid());
        task.setProject(item.getProject().getUuid());
        task.setStatus(item.getStatus());

        return taskRepository.save(task);
    }

    @Transactional(readOnly = true)
    private List<ProjectRefDTO> getFilteredProjectRefs(UserShortDTO user, FilterDTO filterDTO) {
        if (filterDTO.getProject() == null){
            List<Project> userProjects = projectService.readAllByUser(user.getUuid());

            return userProjects.stream().map(p -> new ProjectRefDTO(p.getUuid())).toList();
        }

        if(!user.getRole().equals(EUserRole.ROLE_ADMIN)) {
            List<ProjectRefDTO> availableProjects = new ArrayList<>();

            for (ProjectRefDTO dto : filterDTO.getProject()) {
                Project project = projectService.read(dto.getUuid());
                List<UUID> staff = project.getStuff();

                if (staff.contains(user.getUuid()) || project.getManager().equals(user.getUuid())) {
                    availableProjects.add(dto);
                }
            }

            return availableProjects;
        }

        return filterDTO.getProject();
    }
}
