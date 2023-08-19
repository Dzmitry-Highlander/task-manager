package by.itacademy.jd2.task_service.core.dto.update;

import by.itacademy.jd2.task_service.core.dto.ref.UserRefDTO;
import by.itacademy.jd2.task_service.core.enums.EProjectStatus;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectUpdateDTO {
    @Size(min = 3, max = 20)
    private String name;

    @Size(min = 5, max = 255)
    private String description;

    private UserRefDTO manager;
    private Set<UserRefDTO> staff;
    private EProjectStatus status;
}
