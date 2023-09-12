package by.itacademy.jd2.task_service.service.util;

import by.itacademy.jd2.base_package.core.dto.PageDTO;
import by.itacademy.jd2.task_service.core.dto.data.TaskDTO;
import by.itacademy.jd2.task_service.repository.entity.Task;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;

public class PageConverter {
    public static PageDTO<TaskDTO> convert(Page<Task> tasks, ConversionService conversionService) {
        return new PageDTO<>(tasks.getNumber(),
                tasks.getSize(),
                tasks.getTotalPages(),
                tasks.getTotalElements(),
                tasks.isFirst(),
                tasks.getNumberOfElements(),
                tasks.isLast(),
                tasks.get().map(u -> conversionService.convert(u, TaskDTO.class)).toList());
    }
}
