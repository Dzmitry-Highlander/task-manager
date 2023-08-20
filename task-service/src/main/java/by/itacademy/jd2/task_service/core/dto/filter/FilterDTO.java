package by.itacademy.jd2.task_service.core.dto.filter;

import by.itacademy.jd2.task_service.core.dto.ref.ProjectRefDTO;
import by.itacademy.jd2.task_service.core.dto.ref.UserRefDTO;
import by.itacademy.jd2.task_service.core.enums.ETaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilterDTO {
    private Set<ETaskStatus> statuses;
    private Set<ProjectRefDTO> projects;
    private Set<UserRefDTO> implementers;
}
