package by.itacademy.jd2.task_service.core.dto;

import by.itacademy.jd2.task_service.core.enums.EProjectStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectUpdateDTO {
    @NotNull(message = "Идентификатор проекта обязателен")
    private UUID uuid;

    @NotBlank(message = "Имя проекта обязательно")
    @Size(max = 255, message = "Максимальный размер имени проекта 255 символов")
    private String name;

    private String description;

    @NotNull(message = "Указание менеджера проекта обязательно")
    private UserRefDTO manager;

    private List<UserRefDTO> staff;

    @NotNull(message = "Статус проекта обязателен")
    private EProjectStatus status;

    @NotNull(message = "Версия проекта обязательна")
    private LocalDateTime updateDate;
}
