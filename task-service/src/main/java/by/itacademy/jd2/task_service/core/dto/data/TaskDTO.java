package by.itacademy.jd2.task_service.core.dto.data;

import by.itacademy.jd2.task_service.core.dto.ref.ProjectRefDTO;
import by.itacademy.jd2.task_service.core.dto.ref.UserRefDTO;
import by.itacademy.jd2.task_service.core.enums.ETaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private UUID uuid;
    private Long createDate;
    private Long updateDate;
    private ProjectRefDTO project;
    private String title;
    private String description;
    private ETaskStatus status;
    private UserRefDTO implementer;
}
