package by.itacademy.jd2.base_package.core.dto;

import by.itacademy.jd2.base_package.core.enums.EEssenceType;
import by.itacademy.jd2.base_package.core.dto.UserShortDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditDTO {
    private UUID uuid;
    private Long createDate;
    private UserShortDTO user;
    private String text;
    private EEssenceType type;
    private String id;
}
