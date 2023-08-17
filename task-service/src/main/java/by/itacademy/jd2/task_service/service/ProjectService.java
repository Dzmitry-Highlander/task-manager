package by.itacademy.jd2.task_service.service;

import by.itacademy.jd2.task_service.core.dto.ProjectCreateDTO;
import by.itacademy.jd2.task_service.core.dto.ProjectUpdateDTO;
import by.itacademy.jd2.task_service.core.enums.EProjectStatus;
import by.itacademy.jd2.task_service.dao.api.IProjectRepository;
import by.itacademy.jd2.task_service.dao.entity.Project;
import by.itacademy.jd2.task_service.service.api.IProjectService;
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
    private static final String INCORRECT_DATA = "Неверные данные. Попробуйте еще раз";

    private final IProjectRepository projectRepository;
    private final ConversionService conversionService;

    @Override
    public Project create(ProjectCreateDTO item) {
        return projectRepository.save(Objects.requireNonNull(conversionService.convert(item, Project.class)));
    }

    @Override
    public List<Project> read() {
        return null;
    }

    @Override
    public Project read(UUID uuid) {
        return null;
    }

    @Override
    public Project update(UUID uuid, LocalDateTime version, ProjectUpdateDTO item) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Project> read(int page, int size, boolean archived) {
        if(page < 0 || size < 0){
            throw new IllegalArgumentException(INCORRECT_DATA);
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
