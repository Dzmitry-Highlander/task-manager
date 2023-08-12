package by.itacademy.jd2.audit_service.dao.entity;

import by.itacademy.jd2.base_package.core.enums.EEssenceType;
import by.itacademy.jd2.base_package.core.enums.EUserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "audit", schema = "audit")
public class Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "audit_id")
    private UUID uuid;

    @CreationTimestamp
    @Column(name = "dt_create")
    private LocalDateTime createDate;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "email")
    private String email;

    @Column(name = "fio")
    private String fio;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private EUserRole role;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private EUserRole status;

    @Column(name = "text")
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private EEssenceType type;

    @Column(name = "id")
    private String id;
}
