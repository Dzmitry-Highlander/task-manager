package by.itacademy.jd2.audit_service.core.dto;

import by.itacademy.jd2.audit_service.core.enums.EUserRole;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private UUID uuid;
    private String mail;
    private String fio;
    private EUserRole role;
}
