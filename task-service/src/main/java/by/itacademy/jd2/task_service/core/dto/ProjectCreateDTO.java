package by.itacademy.jd2.task_service.core.dto;

import by.itacademy.jd2.task_service.core.enums.EProjectStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectCreateDTO {
    @NotBlank(message = "Title can't be empty")
    private String name;

    private String description;

    @NotNull(message = "UserRef can't be null")
    private UUID manager;

    private List<UUID> staff;

    @NotNull(message = "Status is mandatory")
    private EProjectStatus status;
}
