package by.itacademy.jd2.user_service.core.dto;

import by.itacademy.jd2.user_service.core.enums.EUserRole;
import by.itacademy.jd2.user_service.core.enums.EUserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private UUID id;
    private Long createDate;
    private Long updateDate;
    private String mail;
    private String fio;
    private EUserRole role;
    private EUserStatus status;
}
