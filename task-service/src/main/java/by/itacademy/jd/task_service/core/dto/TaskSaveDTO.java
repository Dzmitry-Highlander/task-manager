package by.itacademy.jd.task_service.core.dto;

import by.itacademy.jd.task_service.core.enums.ETaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskSaveDTO {
    @NotBlank(message = "ProjectRef can't be empty")
    private ProjectRefDTO project;

    @NotBlank(message = "Title can't be empty")
    private String title;

    private String description;

    @NotNull(message = "Status is mandatory")
    private ETaskStatus status;

    @NotBlank(message = "UserRef can't be empty")
    private UserRefDTO implementer;
}
