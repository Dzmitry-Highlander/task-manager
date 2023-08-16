package by.itacademy.jd2.task_service.dao.entity;

import by.itacademy.jd2.task_service.core.enums.EProjectStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "project", schema = "task")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "project_id")
    private UUID uuid;

    @CreationTimestamp
    @Column(name = "dt_create")
    private LocalDateTime createDate;

    @Version
    @UpdateTimestamp
    @Column(name = "dt_update")
    private LocalDateTime updateDate;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "manger_id")
    private UUID manager;

    @ElementCollection
    @Column(name = "stuff_id")
    private List<UUID> stuff;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EProjectStatus status;
}
