package by.itacademy.jd2.audit_service.service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NoSuchAuditException extends IllegalArgumentException {
    private UUID uuid;
}
