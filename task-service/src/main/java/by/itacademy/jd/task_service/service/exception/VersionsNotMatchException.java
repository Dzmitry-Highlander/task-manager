package by.itacademy.jd.task_service.service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class VersionsNotMatchException extends IllegalStateException {
    private String version;
}