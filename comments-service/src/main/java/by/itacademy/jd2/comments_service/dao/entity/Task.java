package by.itacademy.jd2.comments_service.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @Column(name = "task_id")
    private UUID id;
    @Column(name = "header")
    private String header;

    public Task() {
    }

    public Task(UUID id, String header) {
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
