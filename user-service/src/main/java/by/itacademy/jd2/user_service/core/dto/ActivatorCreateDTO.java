package by.itacademy.jd2.user_service.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivatorCreateDTO {
    private String code;
    private String email;
    private Long createDate;
    private Long expirationDate;
}
