package by.itacademy.jd2.user_service.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.lang.model.type.ErrorType;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StructuredErrorResponseDTO {
    private ErrorType logref;
    private Map<String, String> errors;
}
