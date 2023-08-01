package by.itacademy.jd2.user_service.core.dto;

import by.itacademy.jd2.user_service.core.enums.EUserRole;
import by.itacademy.jd2.user_service.core.enums.EUserStatus;
import lombok.*;

import java.util.UUID;

@Builder
public class UserDTO {
    private UUID id;
    private Long createDate;
    private Long updateDate;
    private String email;
    private String fio;
    private EUserRole role;
    private EUserStatus status;
}
