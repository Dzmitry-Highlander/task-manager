package by.itacademy.jd2.user_service.core.json;

import by.itacademy.jd2.user_service.core.dto.StructuredErrorResponseDTO;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class StructuredErrorResponseSerializer extends JsonObjectSerializer<StructuredErrorResponseDTO> {
    @Override
    protected void serializeObject(
            StructuredErrorResponseDTO dto,
            JsonGenerator generator,
            SerializerProvider provider
    ) throws IOException {
        generator.writeStringField("logref", dto.getLogref().toString().toLowerCase());
        generator.writeArrayFieldStart("errors");
        dto.getErrors().forEach((key, value) -> {

            try {
                generator.writeStartObject();
                generator.writeStringField("field", StringUtils
                        .uncapitalize(key)
                        .replaceAll("([a-z])([A-Z])", "$1_$2")
                        .toLowerCase());
                generator.writeStringField("message", value);
                generator.writeEndObject();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        generator.writeEndArray();
    }
}
