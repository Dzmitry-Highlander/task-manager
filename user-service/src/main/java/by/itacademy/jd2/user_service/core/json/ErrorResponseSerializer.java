package by.itacademy.jd2.user_service.core.json;

import by.itacademy.jd2.user_service.core.dto.ErrorResponseDTO;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonObjectSerializer;

import java.io.IOException;

public class ErrorResponseSerializer extends JsonObjectSerializer<ErrorResponseDTO> {
    @Override
    protected void serializeObject(
            ErrorResponseDTO dto,
            JsonGenerator generator,
            SerializerProvider provider
    ) throws IOException {
        generator.writeStringField("logref", dto.getLogref().toString().toLowerCase());
        generator.writeStringField("message", dto.getMessage());
    }
}
