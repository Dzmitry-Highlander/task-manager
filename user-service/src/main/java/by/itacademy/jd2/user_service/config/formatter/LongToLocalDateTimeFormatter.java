package by.itacademy.jd2.user_service.config.formatter;

import org.springframework.format.Formatter;
import org.springframework.lang.NonNull;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;

public class LongToLocalDateTimeFormatter implements Formatter<LocalDateTime> {
    @NonNull
    @Override
    public LocalDateTime parse(@NonNull String text, @NonNull Locale locale) {
        long milliseconds = Long.parseLong(text);

        return LocalDateTime.ofInstant(Instant.ofEpochMilli(milliseconds), ZoneId.systemDefault());
    }

    @NonNull
    @Override
    public String print(@NonNull LocalDateTime object, @NonNull Locale locale) {
        return object.toString();
    }
}
