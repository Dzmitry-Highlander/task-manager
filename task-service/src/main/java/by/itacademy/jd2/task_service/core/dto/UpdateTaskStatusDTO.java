package by.itacademy.jd2.task_service.core.dto;

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
public class UpdateTaskStatusDTO {
    private UUID uuid;
    private Long updateDate;
    private ETaskStatus status;
}
