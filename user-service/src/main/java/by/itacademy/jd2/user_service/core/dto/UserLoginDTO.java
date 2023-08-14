package by.itacademy.jd2.user_service.core.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {
    @Email(message = "Enter a valid email")
    @NotEmpty(message = "Email can't be empty")
    private String email;

    @NotEmpty(message = "Password can't be empty")
    @Size(min = 4, max = 20, message = "Password length must be from 4 till 20 symbols")
    private String password;
}
