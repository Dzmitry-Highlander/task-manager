package by.itacademy.jd2.user_service.core.enums;

public enum ERole {
    ADMIN("Администратор"),
    USER("Пользователь");

    private final String role;

    ERole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
