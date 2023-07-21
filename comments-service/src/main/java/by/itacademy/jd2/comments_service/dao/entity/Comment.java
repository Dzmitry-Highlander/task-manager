package by.itacademy.jd2.comments_service.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "comment", schema = "comment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
