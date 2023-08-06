package by.itacademy.jd2.audit_service.core.dto;

import by.itacademy.jd2.audit_service.core.enums.EErrorType;
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
