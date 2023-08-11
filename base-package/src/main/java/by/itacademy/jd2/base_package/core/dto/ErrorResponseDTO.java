package by.itacademy.jd2.base_package.core.dto;

import by.itacademy.jd2.base_package.core.enums.EErrorType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDTO {
    private EErrorType logref;
    private String message;
}
