package by.itacademy.jd2.audit_service.core.dto;

import by.itacademy.jd2.audit_service.core.enums.EEssenceType;
import by.itacademy.jd2.base_package.core.dto.UserShortDTO;
import by.itacademy.jd2.base_package.core.enums.EUserRole;
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
    private UserShortDTO user;
    private String text;
    private EEssenceType type;
    private String id;
}
