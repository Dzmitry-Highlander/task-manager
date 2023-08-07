package by.itacademy.jd2.audit_service.core.dto;

import by.itacademy.jd2.audit_service.core.enums.EEssenceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditDTO {
    private UUID uuid;
    private LocalDateTime createDate;
    private UserDTO user;
    private String text;
    private EEssenceType type;
    private String id;
}
