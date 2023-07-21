package by.itacademy.jd2.user_service.core.dto;

import by.itacademy.jd2.user_service.core.enums.ERole;
import by.itacademy.jd2.user_service.core.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserCreateDTO {
    private String mail;
    private String fio;
    private ERole role;
    private EStatus status;
    private String password;
}