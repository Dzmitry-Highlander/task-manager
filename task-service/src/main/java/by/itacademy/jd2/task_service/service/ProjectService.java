package by.itacademy.jd2.task_service.service;

import by.itacademy.jd2.task_service.core.dto.ProjectCreateDTO;
import by.itacademy.jd2.task_service.core.dto.ProjectUpdateDTO;
import by.itacademy.jd2.task_service.core.dto.UserRefDTO;
import by.itacademy.jd2.task_service.dao.api.IProjectRepository;
import by.itacademy.jd2.task_service.dao.entity.Project;
import by.itacademy.jd2.task_service.service.api.IProjectService;
import by.itacademy.jd2.task_service.service.exception.ItemNotFoundException;
import by.itacademy.jd2.task_service.service.exception.VersionsNotMatchException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectService implements IProjectService {
    private static final String PROJECT_NOT_FOUND_ERROR = "Проект с таким uuid не найден";
    private static final String VERSIONS_NOT_MATCH_ERROR = "Версии не совпадают";

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
                .orElseThrow(() -> new ItemNotFoundException(PROJECT_NOT_FOUND_ERROR));
    }

    @Transactional
    @Override
    public Project update(UUID uuid, LocalDateTime version, ProjectUpdateDTO item) {
        Project project = projectRepository.findById(uuid)
                .orElseThrow(() -> new ItemNotFoundException(PROJECT_NOT_FOUND_ERROR));

        if (!version.equals(project.getUpdateDate())) {
            throw new VersionsNotMatchException(VERSIONS_NOT_MATCH_ERROR);
        }

        List<UserRefDTO> stuff = item.getStaff();
        List<UUID> uuids = new ArrayList<>();

        for (UserRefDTO users : stuff) {
            uuids.add(users.getUuid());
        }

        project.setName(item.getName());
        project.setDescription(item.getDescription());
        project.setManager(item.getManager().getUuid());
        project.setStuff(uuids);
        project.setStatus(item.getStatus());

        return projectRepository.save(project);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Project> read(int page, int size, boolean archived) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Project> readAllByUser(UUID uuid) {
        return projectRepository.findAllByStaffContains(uuid);
    }
}
