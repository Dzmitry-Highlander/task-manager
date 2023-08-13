package by.itacademy.jd2.user_service.core.dto;

import by.itacademy.jd2.base_package.core.enums.EUserRole;
import by.itacademy.jd2.user_service.core.enums.EUserStatus;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDTO {
    @Email(message = "Enter a valid email")
    @NotEmpty(message = "Email can't be empty")
    private String email;

    @NotEmpty(message = "FIO can't be empty")
    private String fio;

    @NotEmpty(message = "Role is mandatory")
    private EUserRole role;

    @NotEmpty(message = "Status is mandatory")
    private EUserStatus status;

    @NotEmpty(message = "Password can't be empty")
    @Size(min = 4, message = "Password length must be from 4 till 20 symbols")
    private String password;
}
