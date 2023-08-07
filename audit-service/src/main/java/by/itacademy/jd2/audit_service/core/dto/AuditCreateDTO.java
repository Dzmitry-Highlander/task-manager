package by.itacademy.jd2.audit_service.core.dto;

import by.itacademy.jd.base_pakage.core.enums.EUserRole;
import by.itacademy.jd2.audit_service.core.enums.EEssenceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditCreateDTO {
    private UUID uuid;
    private String mail;
    private String fio;
    private EUserRole role;
    private String text;
    private EEssenceType type;
    private String id;
}
