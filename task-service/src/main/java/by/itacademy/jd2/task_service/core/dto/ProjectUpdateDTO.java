package by.itacademy.jd2.task_service.core.dto;

import by.itacademy.jd2.task_service.core.enums.EProjectStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectUpdateDTO {
    @NotBlank(message = "Имя проекта обязательно")
    @Size(max = 255, message = "Максимальный размер имени проекта 255 символов")
    private String name;

    private String description;

    @NotNull(message = "Указание менеджера проекта обязательно")
    private UUID manager;

    private List<UUID> staff;

    @NotNull(message = "Статус проекта обязателен")
    private EProjectStatus status;
}
