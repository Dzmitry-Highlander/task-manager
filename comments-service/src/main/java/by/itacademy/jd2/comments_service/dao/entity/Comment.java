package by.itacademy.jd2.comments_service.dao.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "comment", schema = "comment")
public class Comment implements Serializable {
    @Id
    @Column(name = "comment_id")
    private UUID id;
    @Column(name = "dt_create")
    private LocalDateTime createDate;
    @Column(name = "text")
    private String text;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id", referencedColumnName = "task_id")
    private Task task;

    public Comment() {
    }

    public Comment(UUID id, LocalDateTime createDate, String text, User user, Task task) {
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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment comment)) return false;
        return Objects.equals(getId(), comment.getId()) && Objects.equals(getCreateDate(),
                comment.getCreateDate()) && Objects.equals(getText(), comment.getText()) && Objects.equals(getUser(),
                comment.getUser()) && Objects.equals(getTask(), comment.getTask());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCreateDate(), getText(), getUser(), getTask());
    }
}
