package by.itacademy.jd2.task_service.service.converter;

import by.itacademy.jd2.task_service.core.dto.ProjectUpdateDTO;
import by.itacademy.jd2.task_service.core.dto.UserRefDTO;
import by.itacademy.jd2.task_service.dao.entity.Project;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProjectUpdateToProjectConverter implements Converter<ProjectUpdateDTO, Project> {
    @Override
    public Project convert(ProjectUpdateDTO source) {
        return Project.builder()
                .name(source.getName())
                .description(source.getDescription())
                .manager(source.getManager())
                .stuff(source.getStaff())
                .status(source.getStatus())
                .build();
    }
}
