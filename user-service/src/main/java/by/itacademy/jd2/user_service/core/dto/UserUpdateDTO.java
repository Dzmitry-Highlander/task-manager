package by.itacademy.jd2.user_service.core.dto;

import by.itacademy.jd2.user_service.core.enums.EUserRole;
import by.itacademy.jd2.user_service.core.enums.EUserStatus;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO {
    @NotEmpty(message = "FIO can't be empty")
    private String fio;

    @NotEmpty(message = "Role is mandatory")
    private EUserRole role;

    @NotEmpty(message = "Status is mandatory")
    private EUserStatus status;
}
