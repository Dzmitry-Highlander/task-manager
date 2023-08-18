package by.itacademy.jd2.task_service.service.api;

import by.itacademy.jd2.task_service.core.dto.ProjectCreateDTO;
import by.itacademy.jd2.task_service.core.dto.ProjectUpdateDTO;
import by.itacademy.jd2.task_service.dao.entity.Project;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface IProjectService extends ICRUDService<Project, ProjectCreateDTO, ProjectUpdateDTO>{
    Page<Project> read(int page, int size, boolean archived);

    List<Project> readAllByUser(UUID uuid);

    boolean ifExist(UUID project, UUID implementer);
}
