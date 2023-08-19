package by.itacademy.jd2.task_service.core.dto.create;

import by.itacademy.jd2.task_service.core.dto.ref.UserRefDTO;
import jakarta.validation.constraints.NotNull;
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
public class ProjectCreateDTO {
    @Size(min = 3, max = 20)
    private String name;

    @Size(min = 5, max = 255)
    private String description;

    @NotNull
    private UserRefDTO manager;

    private Set<UserRefDTO> staff;
}
