package by.itacademy.jd2.task_service.service;

import by.itacademy.jd2.task_service.core.dto.create.ProjectCreateDTO;
import by.itacademy.jd2.task_service.core.dto.ref.UserRefDTO;
import by.itacademy.jd2.task_service.core.dto.update.ProjectUpdateDTO;
import by.itacademy.jd2.task_service.core.exception.VersionsNotMatchException;
import by.itacademy.jd2.task_service.repository.api.IProjectRepository;
import by.itacademy.jd2.task_service.repository.entity.Project;
import by.itacademy.jd2.task_service.service.api.IProjectService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProjectService implements IProjectService {
    //TODO Audit
    private static final String PROJECT_NOT_FOUND_ERROR = "Проект по указанному uuid не найдена";
    private static final String VERSIONS_NOT_MATCH_ERROR = "Версии не совпадают";

    private final IProjectRepository projectRepository;
    private final ConversionService conversionService;

    @Transactional
    @Override
    public Project create(ProjectCreateDTO projectCreateDTO) {
        Project project = conversionService.convert(projectCreateDTO, Project.class);

        return projectRepository.saveAndFlush(Objects.requireNonNull(project));
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Project> read(int page, int size, boolean archived) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public Project read(UUID uuid) {
        return projectRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException(PROJECT_NOT_FOUND_ERROR));
    }

    @Transactional
    @Override
    public Project update(UUID uuid, String version, ProjectUpdateDTO projectUpdateDTO) {
        LocalDateTime localDateTime = conversionService.convert(version, LocalDateTime.class);
        Project project = projectRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException(PROJECT_NOT_FOUND_ERROR));

        if (!Objects.equals(localDateTime, project.getCreateDate())) {
            throw new VersionsNotMatchException(VERSIONS_NOT_MATCH_ERROR);
        }

        Set<UUID> staff = new HashSet<>();

        for (UserRefDTO worker : projectUpdateDTO.getStaff()) {
            staff.add(worker.getUuid());
        }

        project.setName(projectUpdateDTO.getName());
        project.setDescription(projectUpdateDTO.getDescription());
        project.setStatus(projectUpdateDTO.getStatus());
        project.setManager(projectUpdateDTO.getManager().getUuid());
        project.setStaff(staff);

        return projectRepository.saveAndFlush(project);
    }
}
