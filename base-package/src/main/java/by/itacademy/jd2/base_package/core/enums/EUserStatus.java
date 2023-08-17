package by.itacademy.jd2.base_package.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EUserStatus {
    ACTIVATED("activated"),
    DEACTIVATED("deactivated"),
    WAITING_ACTIVATION("waiting activation");

    private final String status;
}
