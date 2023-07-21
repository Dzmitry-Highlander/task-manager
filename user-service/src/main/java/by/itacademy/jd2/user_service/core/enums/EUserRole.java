package by.itacademy.jd2.user_service.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EUserRole {
    ADMIN("Администратор"),
    USER("Пользователь");

    private final String role;
}
