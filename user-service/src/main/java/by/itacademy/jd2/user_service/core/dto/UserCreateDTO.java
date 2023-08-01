package by.itacademy.jd2.user_service.core.dto;

import by.itacademy.jd2.user_service.core.enums.EUserRole;
import by.itacademy.jd2.user_service.core.enums.EUserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDTO {
    @Email(message = "Enter a valid email")
    @NotBlank(message = "Email can't be empty")
    private String email;

    @NotBlank(message = "FIO can't be empty")
    private String fio;

    @NotNull(message = "Role is mandatory")
    private EUserRole role;

    @NotNull(message = "Status is mandatory")
    private EUserStatus status;

    @NotBlank(message = "Password can't be empty")
    @Size(min = 4, max = 20, message = "Password length must be from 4 till 20 symbols")
    private String password;
}
