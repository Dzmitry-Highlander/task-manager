package by.itacademy.jd.task_service.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ETaskStatus {
    WAIT("Ожидает выполнения"),
    BLOCK("Не может быть выполнена"),
    IN_WORK("В работе"),
    DONE("Выполнена"),
    CLOSE("Закрыта");

    private final String status;
}
