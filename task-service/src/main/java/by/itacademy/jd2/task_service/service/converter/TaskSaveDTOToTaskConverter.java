package by.itacademy.jd2.task_service.service.converter;

import by.itacademy.jd2.task_service.core.dto.TaskSaveDTO;
import by.itacademy.jd2.task_service.dao.entity.Task;
import org.springframework.core.convert.converter.Converter;

public class TaskSaveDTOToTaskConverter implements Converter<TaskSaveDTO, Task> {
    @Override
    public Task convert(TaskSaveDTO source) {
        return Task.builder()
                .title(source.getTitle())
                .description(source.getDescription())
                .implementer(source.getImplementer().getUuid())
                .project(source.getProject().getUuid())
                .build();
    }
}
