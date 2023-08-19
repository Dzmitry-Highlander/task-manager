package by.itacademy.jd2.task_service.core.dto.create;

import by.itacademy.jd2.task_service.core.dto.ref.ProjectRefDTO;
import by.itacademy.jd2.task_service.core.dto.ref.UserRefDTO;
import jakarta.validation.constraints.NotBlank;
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
public class TaskCreationDTO {
    @NotNull
    private ProjectRefDTO project;

    @NotBlank
    @Size(min = 3, max = 20)
    private String title;

    @NotBlank
    @Size(max = 255)
    private String description;

    @NotNull
    private UserRefDTO implementer;
}
