package by.itacademy.jd2.task_service.dao.entity;

import by.itacademy.jd2.task_service.core.enums.ETaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task", schema = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "task_id")
    private UUID uuid;

    @CreationTimestamp
    @Column(name = "dt_create")
    private LocalDateTime createDate;

    @Version
    @UpdateTimestamp
    @Column(name = "dt_update")
    private LocalDateTime updateDate;

    @Column(name = "project_id")
    private UUID project;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ETaskStatus status;

    @Column(name = "implementer_id")
    private UUID implementer;
}
