package by.itacademy.jd2.comments_service.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "task", schema = "comment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Task {
    @Id
    @Column(name = "task_id")
    private UUID id;
    @Column(name = "header")
    private String header;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Task task)) return false;

        return Objects.equals(getId(), task.getId()) && Objects.equals(getHeader(), task.getHeader());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getHeader());
    }
}
