package by.itacademy.jd2.user_service.core.dto;

import by.itacademy.jd2.base_package.core.enums.EUserRole;
import by.itacademy.jd2.user_service.core.enums.EUserStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Role is mandatory")
    private EUserRole role;

    @NotNull(message = "Status is mandatory")
    private EUserStatus status;
}
