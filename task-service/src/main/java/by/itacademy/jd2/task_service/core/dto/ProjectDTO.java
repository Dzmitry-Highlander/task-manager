package by.itacademy.jd2.task_service.core.dto;

import by.itacademy.jd2.task_service.core.enums.EProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDTO {
    private UUID uuid;
    private Long createDate;
    private Long updateDate;
    private String name;
    private String description;
    private UUID manager;
    private List<UUID> staff;
    private EProjectStatus status;
}
