package by.itacademy.jd2.task_service.core.dto;

import by.itacademy.jd2.task_service.core.enums.EProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
    private UUID uuid;
    private Long createDate;
    private Long updateDate;
    private String name;
    private String description;
    private UserRefDTO manager;
    private Set<UserRefDTO> staff;
    private EProjectStatus status;
}
