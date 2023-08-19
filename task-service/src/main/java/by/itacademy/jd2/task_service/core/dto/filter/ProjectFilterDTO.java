package by.itacademy.jd2.task_service.core.dto.filter;

import by.itacademy.jd2.task_service.core.dto.ref.ProjectRefDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectFilterDTO {
    private Set<ProjectRefDTO> projects;
}
