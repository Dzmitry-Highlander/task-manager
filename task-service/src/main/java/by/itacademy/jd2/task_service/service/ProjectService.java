package by.itacademy.jd2.task_service.service;

import by.itacademy.jd2.task_service.core.dto.ProjectCreateDTO;
import by.itacademy.jd2.task_service.core.dto.ProjectUpdateDTO;
import by.itacademy.jd2.task_service.core.enums.EProjectStatus;
import by.itacademy.jd2.task_service.dao.api.IProjectRepository;
import by.itacademy.jd2.task_service.dao.entity.Project;
import by.itacademy.jd2.task_service.service.api.IProjectService;
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
public class ProjectService implements IProjectService {
    private static final String INCORRECT_DATA_ERROR = "Введенные данные некорректны";
    private static final String PROJECT_NOT_FOUND_ERROR = "Пользователь с таким uuid не найден";
    private static final String VERSIONS_NOT_MATCH_ERROR = "Версии не совпадают";
    private static final String PROJECT_SAVE_REQUEST = "Запрос на сохранение проекта";

    private final IProjectRepository projectRepository;
    private final ConversionService conversionService;

    @Override
    public Project create(ProjectCreateDTO item) {
        Project project = projectRepository.save(Objects.requireNonNull(
                conversionService.convert(item, Project.class))
        );

        return project;
    }

    @Override
    public List<Project> read() {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public Project read(UUID uuid) {
        return projectRepository.findById(uuid)
                .orElseThrow(() -> new ItemNotFoundException(PROJECT_NOT_FOUND_ERROR));
    }

    @Transactional
    @Override
    public Project update(UUID uuid, LocalDateTime version, ProjectUpdateDTO item) {
        //TODO handle exception
        Project project = projectRepository.findById(uuid)
                .orElseThrow(() -> new ItemNotFoundException(PROJECT_NOT_FOUND_ERROR));

        //TODO handle exception
        if (!version.isEqual(project.getUpdateDate())) {
            throw new VersionsNotMatchException(VERSIONS_NOT_MATCH_ERROR);
        }

        project.setName(item.getName());
        project.setDescription(item.getDescription());
        project.setStatus(item.getStatus());
        project.setManager(item.getManager());
        project.setStuff(item.getStaff());

        return projectRepository.save(project);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Project> read(int page, int size, boolean archived) {
        if(page < 0 || size < 0){
            throw new IllegalArgumentException(INCORRECT_DATA_ERROR);
        }

        PageRequest pageRequest = PageRequest.of(page, size);

        if(!archived){
            return projectRepository.findAllByStatus(pageRequest, EProjectStatus.ACTIVE);
        }

        return projectRepository.findAll(pageRequest);
    }

    @Override
    public List<Project> readAllByUser(UUID uuid) {
        return null;
    }
}
