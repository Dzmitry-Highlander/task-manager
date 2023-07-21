package by.itacademy.jd2.user_service.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EUserStatus {
    ACTIVE("active"),
    BANNED("banned");

    private final String status;
}
