package by.itacademy.jd2.user_service.core.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDTO {
    private String email;
    private String fio;
    private String password;
}
