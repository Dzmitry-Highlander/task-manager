package by.itacademy.jd2.user_service.service.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;

public class DateTimeToLongConverter implements Converter<String, LocalDateTime> {
    @Override
    public LocalDateTime convert(String source) {
        return null;
    }
}
