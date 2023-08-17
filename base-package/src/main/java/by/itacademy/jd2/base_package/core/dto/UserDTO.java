package by.itacademy.jd2.base_package.core.dto;

import by.itacademy.jd2.base_package.core.enums.EUserRole;
import by.itacademy.jd2.base_package.core.enums.EUserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private UUID uuid;
    private Long createDate;
    private Long updateDate;
    private String email;
    private String fio;
    private EUserRole role;
    private EUserStatus status;
}
