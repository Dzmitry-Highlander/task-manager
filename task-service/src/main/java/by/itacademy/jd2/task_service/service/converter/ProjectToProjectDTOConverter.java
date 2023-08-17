package by.itacademy.jd2.task_service.service.converter;

import by.itacademy.jd2.task_service.core.dto.ProjectDTO;
import by.itacademy.jd2.task_service.dao.entity.Project;
import org.springframework.core.convert.converter.Converter;

import java.time.ZoneId;

public class ProjectToProjectDTOConverter implements Converter<Project, ProjectDTO> {
    @Override
    public ProjectDTO convert(Project source) {
        return ProjectDTO.builder()
                .uuid(source.getUuid())
                .createDate(source.getCreateDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .updateDate(source.getUpdateDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .name(source.getName())
                .description(source.getDescription())
                .manager(source.getManager())
                .staff(source.getStuff())
                .status(source.getStatus())
                .build();
    }
}
