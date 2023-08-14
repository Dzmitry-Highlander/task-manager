package by.itacademy.jd2.task_service.core.dto;

import by.itacademy.jd2.task_service.core.enums.ETaskStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskUpdateDTO {
    @NotNull(message = "Указание проекта обязательно")
    private ProjectRefDTO project;

    @Size(max = 255, message = "Максимальный размер имени задачи 255 символов")
    private String title;

    private String description;

    private ETaskStatus status;

    private UserRefDTO implementer;

}
