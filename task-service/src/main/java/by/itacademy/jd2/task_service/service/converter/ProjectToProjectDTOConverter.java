package by.itacademy.jd2.task_service.service.converter;

import by.itacademy.jd2.task_service.core.dto.ProjectDTO;
import by.itacademy.jd2.task_service.core.dto.UserRefDTO;
import by.itacademy.jd2.task_service.dao.entity.Project;
import org.springframework.core.convert.converter.Converter;

import java.time.ZoneId;
import java.util.stream.Collectors;

public class ProjectToProjectDTOConverter implements Converter<Project, ProjectDTO> {
    @Override
    public ProjectDTO convert(Project source) {
        return ProjectDTO.builder()
                .uuid(source.getUuid())
                .createDate(source.getCreateDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .updateDate(source.getUpdateDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .name(source.getName())
                .description(source.getDescription())
                .manager(new UserRefDTO(source.getManager()))
                .staff(source.getStuff().stream().map(UserRefDTO::new).collect(Collectors.toList()))
                .status(source.getStatus())
                .build();
    }
}
