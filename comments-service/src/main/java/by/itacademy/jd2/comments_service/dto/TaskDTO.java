package by.itacademy.jd2.comments_service.dto;

import java.util.UUID;

public class TaskDTO {
    private UUID id;
    private String header;

    public TaskDTO() {
    }

    public TaskDTO(UUID id, String header) {
        this.id = id;
        this.header = header;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
