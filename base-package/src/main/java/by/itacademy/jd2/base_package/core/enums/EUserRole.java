package by.itacademy.jd2.base_package.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EUserRole {
    ROLE_ADMIN("Администратор"),
    ROLE_USER("Пользователь"),
    ROLE_SYSTEM("Система");

    private final String role;
}
