package by.itacademy.jd2.user_service.core.enums;

public enum EUserRole {
    ADMIN("Администратор"),
    USER("Пользователь");

    private final String role;

    EUserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
