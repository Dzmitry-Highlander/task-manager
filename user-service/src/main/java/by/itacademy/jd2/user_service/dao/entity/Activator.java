package by.itacademy.jd2.user_service.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "activator", schema = "users")
public class Activator implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "activator_id")
    private Long id;

    @CreationTimestamp
    @Column(name = "dt_create")
    private LocalDateTime createDate;

    @Column(name = "email")
    private String email;

    @Column(name = "code")
    private Integer code;

    @Column(name = "dt_expiration")
    private LocalDateTime expirationDate;
}
