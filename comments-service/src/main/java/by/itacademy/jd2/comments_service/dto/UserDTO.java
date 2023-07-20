package by.itacademy.jd2.comments_service.dto;

import java.util.UUID;

public class UserDTO {
    private UUID id;
    private String fullName;

    public UserDTO() {
    }

    public UserDTO(UUID id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
