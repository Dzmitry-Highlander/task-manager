package by.itacademy.jd2.task_service.service.converter;

import by.itacademy.jd2.task_service.core.dto.ProjectCreateDTO;
import by.itacademy.jd2.task_service.core.dto.UserRefDTO;
import by.itacademy.jd2.task_service.dao.entity.Project;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProjectCreateDTOToProjectConverter implements Converter<ProjectCreateDTO, Project> {
    @Override
    public Project convert(ProjectCreateDTO source) {
        List<UserRefDTO> stuff = source.getStaff();
        List<UUID> uuids = new ArrayList<>();

        for (UserRefDTO users : stuff) {
            uuids.add(users.getUuid());
        }

        return Project.builder()
                .name(source.getName())
                .description(source.getDescription())
                .manager(source.getManager().getUuid())
                .stuff(uuids)
                .build();
    }
}
