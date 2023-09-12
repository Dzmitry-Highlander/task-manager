package by.itacademy.jd2.task_service.service.api;

import by.itacademy.jd2.task_service.core.dto.create.ProjectCreateDTO;
import by.itacademy.jd2.task_service.core.dto.update.ProjectUpdateDTO;
import by.itacademy.jd2.task_service.repository.entity.Project;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface IProjectService {
    Project create(ProjectCreateDTO projectCreateDTO);

    Page<Project> read(
            int page,
            int size,
            boolean archived
    );

    Project read(UUID uuid);

    Project update(UUID uuid, String version, ProjectUpdateDTO projectUpdateDTO);
}
