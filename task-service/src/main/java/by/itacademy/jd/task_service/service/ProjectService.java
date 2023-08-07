package by.itacademy.jd.task_service.service;

import by.itacademy.jd.task_service.core.dto.ProjectCreateDTO;
import by.itacademy.jd.task_service.dao.api.IProjectRepository;
import by.itacademy.jd.task_service.dao.entity.Project;
import by.itacademy.jd.task_service.service.api.IProjectService;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProjectService implements IProjectService {
    private final IProjectRepository projectRepository;
    private final ConversionService conversionService;

    @Transactional
    @Override
    public Project create(ProjectCreateDTO item) {
        return projectRepository.save(Objects.requireNonNull(conversionService.convert(item, Project.class)));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Project> read() {
        return projectRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Project read(UUID uuid) {
        return projectRepository.findById(uuid)
                //TODO Custom Exception
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public Project update(UUID uuid, Long version, ProjectCreateDTO item) {
        return null;
    }
}
