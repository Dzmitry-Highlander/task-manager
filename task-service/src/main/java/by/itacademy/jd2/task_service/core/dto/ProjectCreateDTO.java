package by.itacademy.jd2.task_service.core.dto;

import by.itacademy.jd2.task_service.core.enums.EProjectStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectCreateDTO {
    @NotBlank(message = "Title can't be empty")
    private String name;

    private String description;

    @NotNull(message = "UserRef can't be null")
    private UserRefDTO manager;

    private List<UserRefDTO> staff;

    @NotNull(message = "Status is mandatory")
    private EProjectStatus status;
}
