package by.itacademy.jd2.user_service.config.formatter;

import by.itacademy.jd2.user_service.core.dto.ErrorResponseDTO;
import by.itacademy.jd2.user_service.core.enums.EErrorType;
import org.springframework.format.Formatter;
import org.springframework.lang.NonNull;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LongToLocalDateTimeFormatter implements Formatter<LocalDateTime> {
    private static final String NOT_CORRECT_INPUT_ERROR = "Введены некорректные символы, допустимы только цифры";

    @NonNull
    @Override
    public LocalDateTime parse(@NonNull String text, @NonNull Locale locale) throws ParseException {
        try {
            long milliseconds = Long.parseLong(text);

            return LocalDateTime.ofInstant(Instant.ofEpochMilli(milliseconds), ZoneId.systemDefault());
        } catch (NumberFormatException e) {
            List<ErrorResponseDTO> errorResponseDTOS = new ArrayList<>();

            errorResponseDTOS.add(new ErrorResponseDTO(EErrorType.ERROR, NOT_CORRECT_INPUT_ERROR));

            throw new RuntimeException();
        }
    }

    @NonNull
    @Override
    public String print(@NonNull LocalDateTime object, @NonNull Locale locale) {
        return object.toString();
    }
}
