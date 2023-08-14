package by.itacademy.jd2.task_service.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EProjectStatus {
    ACTIVE("Активный"),
    ARCHIVED("Архивный");

    private final String status;
}
