package by.itacademy.jd2.user_service.service.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class LongToDateTimeConverter implements Converter<String, LocalDateTime> {
    @Override
    public LocalDateTime convert(@NonNull String source) {
        Instant instant = Instant.ofEpochMilli(Long.parseLong(source));

        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }
}
