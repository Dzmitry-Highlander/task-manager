package by.itacademy.jd2.base_package.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EEssenceType {
    USER("Пользователь"),
    PROJECT("Проект");

    private final String type;
}
