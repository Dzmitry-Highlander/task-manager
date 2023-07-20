package by.itacademy.jd2.comments_service.core.dto;

import java.util.UUID;

public class CommentDTO {
    private UUID id;
    private Long createDate;
    private String text;
    private UserDTO user;
    private TaskDTO task;

    public CommentDTO() {
    }

    public CommentDTO(UUID id, Long createDate, String text, UserDTO user, TaskDTO task) {
        this.id = id;
        this.createDate = createDate;
        this.text = text;
        this.user = user;
        this.task = task;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public TaskDTO getTask() {
        return task;
    }

    public void setTask(TaskDTO task) {
        this.task = task;
    }
}
