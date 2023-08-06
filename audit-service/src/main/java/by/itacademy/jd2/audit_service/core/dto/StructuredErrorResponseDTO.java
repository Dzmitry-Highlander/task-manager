package by.itacademy.jd2.audit_service.core.dto;

import by.itacademy.jd2.audit_service.core.enums.EErrorType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StructuredErrorResponseDTO {
    private EErrorType logref;
    private Map<String, String> errors;
}