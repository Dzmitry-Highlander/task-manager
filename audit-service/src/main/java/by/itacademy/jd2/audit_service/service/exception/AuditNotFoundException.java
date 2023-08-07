package by.itacademy.jd2.audit_service.service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuditNotFoundException extends IllegalArgumentException {
    private String text;
}
