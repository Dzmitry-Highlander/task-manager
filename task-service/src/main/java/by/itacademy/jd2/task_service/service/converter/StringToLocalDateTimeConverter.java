package by.itacademy.jd2.task_service.service.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {
    private static final String DATE_TIME_ERROR = "Неверная дата";

    @Override
    public LocalDateTime convert(@NonNull String source) {
        try {
            Instant instant = Instant.ofEpochMilli(Long.parseLong(source));

            return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        } catch (DateTimeException e) {
            throw new DateTimeException(DATE_TIME_ERROR);
        }
    }
}
