package by.itacademy.jd2.user_service.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "activator", schema = "users")
public class Activator implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "activator_id")
    private UUID id;

    @CreationTimestamp
    @Column(name = "dt_create")
    private LocalDateTime createDate;

    @Column(name = "email")
    private String email;

    @Column(name = "code")
    private String code;

    @Column(name = "dt_expiration")
    private LocalDateTime expirationDate;
}
