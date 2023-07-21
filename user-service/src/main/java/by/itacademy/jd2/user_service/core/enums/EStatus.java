package by.itacademy.jd2.user_service.core.enums;

public enum EStatus {
    ACTIVE("active"),
    BANNED("banned");

    private final String status;

    EStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
