package by.itacademy.jd2.user_service.core.dto;

import by.itacademy.jd2.user_service.core.enums.EUserRole;
import by.itacademy.jd2.user_service.core.enums.EUserStatus;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDTO {
    private String email;
    private String fio;
    private EUserRole role;
    private EUserStatus status;
    private String password;
}
