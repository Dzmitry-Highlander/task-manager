package by.itacademy.jd2.base_package.core.dto;

import by.itacademy.jd2.base_package.core.enums.EEssenceType;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditCreateDTO {
    private UserShortDTO user;

    @NotEmpty(message = "Text can't be empty")
    private String text;

    @NotEmpty(message = "Type is mandatory")
    private EEssenceType type;

    @NotEmpty(message = "Id can't be empty")
    private String id;
}
