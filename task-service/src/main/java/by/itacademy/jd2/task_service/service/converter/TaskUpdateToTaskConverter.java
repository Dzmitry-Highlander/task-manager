package by.itacademy.jd2.task_service.service.converter;

import by.itacademy.jd2.task_service.core.dto.TaskUpdateDTO;
import by.itacademy.jd2.task_service.dao.entity.Task;
import org.springframework.core.convert.converter.Converter;

public class TaskUpdateToTaskConverter implements Converter<TaskUpdateDTO, Task> {

    @Override
    public Task convert(TaskUpdateDTO source) {
        return Task.builder()
                .project(source.getProject().getUuid())
                .title(source.getTitle())
                .description(source.getDescription())
                .status(source.getStatus())
                .implementer(source.getImplementer().getUuid())
                .build();
    }
}
