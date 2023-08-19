package by.itacademy.jd2.task_service.core.dto.update;

import by.itacademy.jd2.task_service.core.dto.ref.UserRefDTO;
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
    @Size(min = 3, max = 255)
    private String title;

    @Size(min = 5, max = 255)
    private String description;

    @NotNull
    private ETaskStatus status;

    @NotNull
    private UserRefDTO implementer;
}
