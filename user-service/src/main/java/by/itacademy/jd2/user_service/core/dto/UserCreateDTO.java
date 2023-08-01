package by.itacademy.jd2.user_service.core.dto;

import by.itacademy.jd2.user_service.core.enums.EUserRole;
import by.itacademy.jd2.user_service.core.enums.EUserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserCreateDTO {
    private String email;
    private String fio;
    private EUserRole role;
    private EUserStatus status;
    private String password;
}
