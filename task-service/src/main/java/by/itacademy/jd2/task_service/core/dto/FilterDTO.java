package by.itacademy.jd2.task_service.core.dto;

import by.itacademy.jd2.task_service.core.enums.ETaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilterDTO {
    private List<ETaskStatus> status;
    private List<ProjectRefDTO> project;
    private List<UserRefDTO> implementer;
}
