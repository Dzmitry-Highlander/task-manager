package by.itacademy.jd2.task_service.service.api;

import by.itacademy.jd2.task_service.core.dto.ProjectCreateDTO;
import by.itacademy.jd2.task_service.dao.entity.Project;
import org.springframework.data.domain.Page;

public interface IProjectService extends ICRUDService<Project, ProjectCreateDTO>{
    Page<Project> read(int page, int size, boolean archived);
}
