package by.itacademy.jd2.user_service.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivatorDTO {
    private UUID id;
    private Integer code;
    private String email;
    private Long createDate;
    private Long expirationDate;
}
