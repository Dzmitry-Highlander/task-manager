package by.itacademy.jd2.user_service.core.enums;

public enum EUserStatus {
    ACTIVE("active"),
    BANNED("banned");

    private final String status;

    EUserStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
