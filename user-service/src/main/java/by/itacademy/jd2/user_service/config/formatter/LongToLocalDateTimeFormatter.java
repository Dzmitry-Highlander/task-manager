package by.itacademy.jd2.user_service.config.formatter;

import org.springframework.format.Formatter;
import org.springframework.lang.NonNull;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;

public class LongToLocalDateTimeFormatter implements Formatter<LocalDateTime> {
    private static final String NOT_CORRECT_INPUT_ERROR = "Введены некорректные символы, допустимы только цифры";

    @NonNull
    @Override
    public LocalDateTime parse(@NonNull String text, @NonNull Locale locale) {
        try {
            long milliseconds = Long.parseLong(text);

            return LocalDateTime.ofInstant(Instant.ofEpochMilli(milliseconds), ZoneId.systemDefault());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_CORRECT_INPUT_ERROR);
        }
    }

    @NonNull
    @Override
    public String print(@NonNull LocalDateTime object, @NonNull Locale locale) {
        return object.toString();
    }
}
