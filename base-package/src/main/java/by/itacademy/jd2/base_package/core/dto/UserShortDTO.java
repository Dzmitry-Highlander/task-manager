package by.itacademy.jd2.base_package.core.dto;

import by.itacademy.jd2.base_package.core.enums.EUserRole;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserShortDTO {
    @NotNull(message = "Uuid is mandatory")
    private UUID uuid;
    @Email
    @NotEmpty(message = "Email is mandatory")
    private String email;
    @NotBlank(message = "Fio is mandatory")
    private String fio;
    @NotNull(message = "Role is mandatory")
    private EUserRole role;
}
